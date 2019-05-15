# 안드로이드 개발자 사이트 학습하기

[TOC]

## 서론

2012년 7월부터 시작하여 2019년 5월 현재까지 대략 7년정도 안드로이드를 개발해왔다. 뒤돌아보면 직접 개발에 참여한 프로젝트중에 그래도 어느정도 마무리를 했다 싶은 프로젝트는 2개 남짓. 나머지 프로젝트는 대부분 중간에 싸워서 관두거나, 회사사정으로 중단되거나, 프리랜서로 프로젝트 투입되면서 일부분만 개발한게 다 인것 같다. 물론 개발 경험과 지식은 많이 늘었지만 외적으로 나를 어필 할 수 있는 것은 이력서에 어떤것을 개발했다 정도. 그래서 구글 Material Design과 Develop 사이트에 있는 내용을 공부하고, 정리하며 이 프로잭트를 진행하려한다.

### Activity

#### 화면의 시작

엑티비티에 대해 알아보자. 개발자 사이트에서는 굉장히 중요한 컴포넌트라고 한다. 기본적으로 하나의 화면을 만들 때 먼저 뷰(View)라는 클래스 만들어 이안에 화면에 대한 정보를 담는다. 엑티비티는 이 뷰를 화면에 보일 수 있게 수행 할 수 있다. 그러므로 내가 작업한 레이아웃을 안드로이드 폰에 보이게 하려면 이 액티비티를 생성해야한다.

#### Context

엑티비티에는 Context라는 클래스가 있는데 이 Context를 통해 폰안에 있는 기능을 거이 사용할 수 있으므로 굉장히 중요한 기능을 한다. 예를들면, GPS센서 사용, 전화, 문자, 데이터베이스 등 거이 모든기능이 이 Context를 사용해서 기능을 동작 시킬 수 있다. 그래서 이 Context를 아무 클래스에서나 전역변수로 생성해서 할당하고 사용하는데, 이렇게 하면 당장 개발은 편할 수 있으나, 메모리 leak가 발생하거나, 엑티비티 종료 시 Context가 null이되어 앱이 비정상적 종료가 발생할 수 있으므로 주의해서 사용해야한다. 다른 클래스에서 사용할 경우 엑티비티를 통해 파라미터로 전달받고, 함수안에서만 지역변수로 사용해야하는것이 좋다.

#### 라이프사이클

이건 엑티비티를 사용할 때 반드시 알아야 하는 개념으로 많은 곳에 자세하게 설명되어있다.

<https://developer.android.com/guide/components/activities/activity-lifecycle>

엑티비티를 생성하여 라이프사이클에 해당하는 함수에 로그를 찍고 직접 동작해보면서, 로그를 확인하면 좀 더 빠르게 이해할 수 있다.

#### Intent

보통 화면을 이동할 때 사용하며, Intent에 값을넣고 다른 화면을 호출하면 다른화면에서 데이터를 받아서 연속적으로 데이터를 처리할 수 있다. startActivityForResult같은 기능을 사용하면 다른화면에서 다시 이전화면으로 돌아왔을 때, 데이터를 전달 받을 수 있다.

#### 화면 스택 처리

엑티비티는 보통 스택으로 처리가되는데, 개발을 하다보면 어떤 특정상황에서, 전의 전 화면으로 간다던지 메인화면으로 가는 기능이 필요하다 이 때 엑티비티의 이러한 부분의 해결을 위해 옵션을 제공한다. 이 기능은 설명보단 직접 예를 만들어 개발해보면서 하는게 좋다.



### 예제

위에 설명한 기능들에대해 개발을 해보자

#### 액티비티 생성하기

<img src = image/activity/0.png width=600px>

안드로이드 스튜디오에서 메뉴를 선택하면 엑티비티를 편하게 추가 할 수 있다.

<img src = image/activity/1.png width=600px>

여기서 알아야 할 점은 위 메뉴를 통해 엑티비티를 추가하게되면 자동으로 AndroidManifest 파일에 해당 엑티비티가 등록이 된다. 엑티비티를 동작하려면 반드시 AndroidManifest파일에 엑티비티를 등록해줘야한다.

<img src = image/activity/2.png width=600px>

아래와 같이 화면에 대한 리소스도 자동으로 추가된다.

<img src = image/activity/3.png width=600px>



#### Context 사용하기

정리하다 보니 너무나도 많은 기능을 담고 있고, 추후에 배울 기능에 기본적으로 요청할 때 사용 때 사용하므로 익히면서 사용하도록 하자.

```
public class ContextSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_sample);

        Context context = this;

        /**
         System Service 사용
         */
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        /**
         리소스 불러오기
         */
        context.getResources().getString(R.string.app_name);

        /**
         클래스에 context를 할당하여 사용하지 말자
         */
        new sample().setContext(context);

    }

    public class sample {
        Context context;

        @Deprecated
        public void setContext(Context context) {

        }
    }
}
```



#### 화면 스택 처리하기

액티비티 스택 처리부분에 대해서 알아보자

##### FLAG_ACTIVITY_CLEAR_TOP

플레그를 가지고 여러방법으로 테스트 해봤지만 눈에 들어오는 기능은 FLAG_ACTIVITY_CLEAR_TOP 와 FLAG_ACTIVITY_SINGLE_TOP 두가지이다.

<img src = image/activity/flag/0.jpg width = 400px>

<img src = image/activity/flag/1.jpg width = 400px>

위 두가지 그림을 보자. 첫번째는 StackActivity를 실행후 Stack1Activity을 4번 실행하였다. 총 5개의 엑티비티 스택이 쌓여있으며, 여기서 FLAG_ACTIVITY_CLEAR_TOP 으로 StackActivity를 다시 실행하였다.  그러면 이전에 StackActivity를 기준으로 그 다음에 실행되었던 엑티비티를 모두 종료 시킨 걸 볼 수 있다. 빨강색 배경이 액티비티가 종료되었다는 표시이다.

 StackActivity는 기존에 있던 엑티비티는 종료되고 새로 생성된다는것을 주의해야한다.



### Fragment

하나의 화면에 여러 화면을 보여줘야할때 주로 사용된다. 사용자의 동작에 따라 화면전환처리를 하는데 3가지 정도 방법을 사용했던것 같다. 엑티비티를 이용해 화면을 전환하는방법, 프레그먼트를 통해 전환하는방법, 뷰를 통해 전환하는 방법.

엑티비티를 이용해 화면을 전환하는 방법은 뭐 가장처음에 안드로이드를 배울 때, 필수적인 요소이기때문에 가장 익숙할 것이다. 그러다보니 프레그먼트사용이 꺼려진다. 엑티비티를 통해 전환을 하면 이전 정보대한 부분이 거이 모두 저장되며 화면이 전환되기때문에 상당히 편하기때문에다. 화면을 이동할때나 뒤로갈 때, 데이터를 전달하는방법도 간편하기때문에 화면 이동에 따른 데이터처리나, 오류처리도 쉬운편이다. 그래서 나도 아직까지 모든화면전환은 엑티비티를 사용한다. 안전하며 개발하기 편하기때문이다. 단점이라면 화면이동 시 약간의 시간이 발생하는정도?

프레그먼트를 사용하면 화면을 넓고 다양하게 사용할 수 있다. 뷰페이저를 통해 페이지 방식으로 구현이 가능하며(뷰를통해서 하는방법도 있는데 옛날방법으로 잘 사용하지 않는다.) 액티비티는 전환 시 무조건 새로운 화면을 띄워야 하지만, 프레그먼트는 현재 보고있는 화면에서 동적으로 화면을 전환 할 수 있다. 단점이라면 그만큼 구현하기가 어려워진다. 하나의 화면에서 여러개의 프레그먼트를 동작시키면, 각 프레그먼트에대한 관리를 잘 해야하며, 데이터 전달이라던지, 화면 전환 네비게이션 등, 훨씬 신경쓸게 많아진다. 그렇기 때문에 많은 연습을 하고 각종 상황에 어떻게 대처할지 판단이 설 때, 프로젝트에 적용하는게 좋다.