package c.com.learningrx.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import c.com.learningrx.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.ConnectableObservable;

public class RegisterActivity extends AppCompatActivity {

    List tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        List<String> list = new ArrayList<>();
        list.add("H");
        list.add("e");
        list.add("l");
        list.add("l");
        list.add("o");
        list.add(" ");
        list.add("W");
        list.add("o");
        list.add("r");
        list.add("l");
        list.add("d");


        Observable.fromIterable(list).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return !s.equalsIgnoreCase("o");
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("tag","value list is - "+s);
            }
        });

        Integer[] data = {1,2,3,4};
        Observable.fromArray(data).
                map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer*integer;
                    }
                }).
                map(new Function<Integer,String>() {

                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer+"-value";
                    }
                }).

                forEach(new Consumer<String>() {
                    @Override
                    public void accept(String integer) throws Exception {
                        Log.i("tag","value int is - "+integer);

                    }
                });


        String datatest = "Hello World";
        Integer i = 4500;
        Boolean b = true;
        Observable.just(datatest,i,b).subscribe(new Observer<Serializable>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Serializable serializable) {
                Log.i("tag","value serializable "+serializable);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        tasks = Arrays.asList(new Tasks(1,"description"),
                new Tasks(2,"description"),new Tasks(4,"description"),
                new Tasks(3,"description"),new Tasks(5,"description"));

/*
        Observable.fromIterable(tasks).forEach(new Consumer<Tasks>() {
            @Override
            public void accept(Tasks tasks) throws Exception {
                Log.i("tag","value serializable "+tasks.id +" -- "+tasks.description);
            }
        });
*/
        Observable.fromIterable(tasks).subscribe(new Observer<Tasks>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Tasks tasks) {
                Log.i("tag","value serializable "+tasks.id +" -- "+tasks.description);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


// cold observable

        List<String > poemsPlayList = Arrays.asList("Poem 1", "Poem 2", "Poem 3");
        Observable coldMusicCoffeCafe = Observable.fromArray(poemsPlayList);

        Consumer consumer1 = new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("tag","consumer one "+o);
            }
        };

        Consumer consumer2 = new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("tag","consumer two "+o);
            }
        };

        Consumer consumer3 = new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("tag","consumer three "+o);
            }
        };

        coldMusicCoffeCafe.subscribe(consumer1);
        coldMusicCoffeCafe.subscribe(consumer2);
        coldMusicCoffeCafe.subscribe(consumer3);


// Hot observable


        List<String > poemsPlayList1 = Arrays.asList("Poem 1", "Poem 2", "Poem 3");
        Observable hotMusicCoffeeCafe = Observable.fromArray(poemsPlayList1);

        // Observable<String> hotMusicCoffeeCafe =Arrays.asList("Poem 1", "Poem 2", "Poem 3");
        ConnectableObservable<String> connectableObservable = hotMusicCoffeeCafe.publish();
        connectableObservable.connect(); //  Cafe open on this line and cafe boy start the system

        Consumer hotConsumer1 = new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("tag","consumer1 one "+o);
            }
        };

        Consumer hotConsumer2 = new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("tag","consumer2 two "+o);
            }
        };

        Consumer hotConsumer3 = new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("tag","consumer3 three "+o);
            }
        };
        Consumer hotConsumer4 = new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("tag","consumer4 three "+o);
            }
        };


        try {
            Thread.sleep(2000); // After two poems already played client 1 enter. So he should listens from poem 2.
            connectableObservable.subscribe(hotConsumer1);
            Thread.sleep(1000); // Client two should start listening poem 3
            connectableObservable.subscribe(hotConsumer2);

            Thread.sleep(4000); // Client 3 and 4 enter will start from poem 9.
            connectableObservable.subscribe(hotConsumer3);
            connectableObservable.subscribe(hotConsumer4);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//hot observable (same stream for all subscribers)

        final   Random random = new Random();
        Observable<Integer> just = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                int value = random.nextInt();
                Log.i("tag","random value"+value);
                e.onNext(value);
            }
        });
        ConnectableObservable<Integer> publish = just.publish();

        publish.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("tag","random value1"+integer);
            }
        });
        publish.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("tag","random value2"+integer);
            }
        });
        publish.connect();


// cold observable (Different streams for different users)

        final   Random random1 = new Random();
        Observable<Integer> just1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                int value = random1.nextInt();
                Log.i("tag","random value10"+value);
                e.onNext(value);
            }
        });
        just1.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("tag","random value11"+integer);
            }
        });
        just1.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("tag","random value12"+integer);
            }
        });


        // dispose() method is nothing but divorce between observer and observable



        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable disposable) {
                disposable.dispose();
            }

            @Override
            public void onNext(Object o) {
                    Log.i("tag","get observable value"+o);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };

        getObservable().subscribe(observer);

    }



    private static Observable<Object> getObservable(){
        return Observable.create(new ObservableOnSubscribe<Object>( ) {
            @Override
            public void subscribe(final ObservableEmitter<Object> observableEmitter) throws Exception {
                Observable.interval(1000, TimeUnit.MILLISECONDS)
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                    observableEmitter.onNext(aLong);
                            }
                        });
            }
        });
    }

    private static class Tasks {
        int id;String description;
        public Tasks(int id, String description) {this.id = id;this.description = description;}
        @Override
        public String toString() {return "Tasks{" + "id=" + id + ", description='" + description + '\'' + '}';}
    }
}
