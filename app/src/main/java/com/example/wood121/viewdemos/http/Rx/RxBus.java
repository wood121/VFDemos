package com.example.wood121.viewdemos.http.Rx;

import android.support.annotation.NonNull;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @author wood121
 * @data 2018/8/2 13
 * @desc
 */
public class RxBus {
    private static RxBus instance;
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap();
    private ConcurrentHashMap<Object, ConcurrentHashMap<Object, Observable<?>>> subcriber = new ConcurrentHashMap();
    private ConcurrentHashMap<Object, List<Subscription>> subscriptionMapper = new ConcurrentHashMap();

    private RxBus() {
    }

    public static synchronized RxBus $1() {
        if (null == instance) {
            instance = new RxBus();
        }

        return instance;
    }

    public static RxBus $() {
        return getInstance();
    }

    public static RxBus getInstance() {
        if (null == instance) {
            Class var0 = RxBus.class;
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }

        return instance;
    }

    public static boolean isEmpty(Collection<Subject> collection) {
        return null == collection || collection.isEmpty();
    }

    public RxBus OnEvent(Observable<?> mObservable, Consumer<Object> mAction1) {
        mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(mAction1, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
        return $();
    }

    public <T> Observable<T> register(@NonNull Object tag) {
        List<Subject> subjectList = (List) this.subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList();
            this.subjectMapper.put(tag, subjectList);
        }

        PublishSubject subject;
        ((List) subjectList).add(subject = PublishSubject.create());
        return subject;
    }

    public <T> Observable<T> register(@NonNull Object tag, @NonNull Consumer<T> mAction1) {
        List<Subject> subjectList = (List) this.subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList();
            this.subjectMapper.put(tag, subjectList);
        }

        PublishSubject subject;
        ((List) subjectList).add(subject = PublishSubject.create());
//        Log.d("uuok.register" + tag + "  size:" + ((List)subjectList).size());
        subject.observeOn(AndroidSchedulers.mainThread()).subscribe(mAction1, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
        return subject;
    }

    public <T> Observable<T> register(@NonNull Object tag, @NonNull Consumer<T> mAction1, Class clasz) {
//        Logc.d("uuu %% " + this.getClass().toString());
        List<Subject> subjectList = (List) this.subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList();
            this.subjectMapper.put(tag, subjectList);
        }

        PublishSubject subject;
        ((List) subjectList).add(subject = PublishSubject.create());
        Subscription sub = (Subscription) subject.observeOn(AndroidSchedulers.mainThread()).subscribe(mAction1, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
        List<Subscription> subList = (List) this.subscriptionMapper.get(clasz);
        if (null == subList) {
            subList = new ArrayList();
        }

        ((List) subList).add(sub);
        this.subscriptionMapper.put(clasz, subList);
        ConcurrentHashMap<Object, Observable<?>> subMap = (ConcurrentHashMap) this.subcriber.get(clasz);
        if (subMap == null) {
            subMap = new ConcurrentHashMap();
        }

        subMap.put(tag, subject);
        this.subcriber.put(clasz, subMap);
        return subject;
    }

    public void clear(Class clazz) {
        this.unsubscribe(clazz);
        ConcurrentHashMap<Object, Observable<?>> sub = (ConcurrentHashMap) this.subcriber.get(clazz);
        if (sub != null) {
            Iterator it = sub.keySet().iterator();

            while (it.hasNext()) {
                Object tag = it.next();
                if (tag != null) {
                    Observable<?> value = (Observable) sub.get(tag);
                    if (value != null) {
                        this.unregister(tag, value);
                    }
                }
            }
        }

    }

    private void unsubscribe(Class clazz) {
        List<Subscription> sub = (List) this.subscriptionMapper.remove(clazz);
        if (sub != null && !sub.isEmpty()) {
            Iterator it = sub.iterator();

            while (it.hasNext()) {
                Subscription subscriop = (Subscription) it.next();
                subscriop.cancel();
                it.remove();
            }
        }

    }

    public void unregister(@NonNull Class clazz) {
        this.clear(clazz);
    }

    public void unregister(@NonNull Object tag) {
        List<Subject> subjects = (List) this.subjectMapper.get(tag);
        if (null != subjects) {
            this.subjectMapper.remove(tag);
        }

    }

    public RxBus unregister(@NonNull Object tag, @NonNull Observable<?> observable) {
        List<Subject> subjects = (List) this.subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove(observable);
            if (isEmpty(subjects)) {
                this.subjectMapper.remove(tag);
            }
        }

        return $();
    }

    public void post(@NonNull Object content) {
        this.post(content.getClass().getName(), content);
    }

    public void post(@NonNull Object tag, @NonNull Object content) {
//        Log.d("posteventName: " + tag);
        List<Subject> subjectList = (List) this.subjectMapper.get(tag);
        if (!isEmpty(subjectList)) {
            for (int i = 0; i < subjectList.size(); ++i) {
                Subject subject = (Subject) subjectList.get(i);
                if (subject != null) {
                    subject.onNext(content);
//                    Logc.d("eventName: " + tag + " subject:" + subject.hasObservers());
                }
            }
        }

    }

}
