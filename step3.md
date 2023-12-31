![image](https://i.imgur.com/eGUtWpf.png)

# 3단계 - 자동차 경주

## 자동차 경주 게임 구현 시작

### 기능 요구 사항

- 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다.
- 사용자는 몇 대의 자동차로 몇 번의 이동을 할 것인지를 입력할 수 있어야 한다.
- 전진하는 조건은 0에서 9 사이에서 무작위 값을 구한 후 무작위 값이 4 이상일 경우이다.
- 자동차의 상태를 화면에 출력한다. 어느 시점에 출력할 것인지에 대한 제약은 없다.

## 실행 결과

위 요구 사항에 따라 3대의 자동차가 5번 움직였을 경우 프로그램을 실행한 결과는 다음과 같다.

```
자동차 대수는 몇 대인가요?
3
시도할 횟수는 몇 회인가요?
5

실행 결과
-
-
-

--
-
--

---
--
---

----
---
----

----
----
-----
```

### 프로그래밍 요구 사항
- 모든 로직에 단위 테스트를 구현한다. 단, UI(println 등) 로직은 제외 
  - 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다. 
  - UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.

### 힌트

- 값을 입력 받는 API는 kotlin.io의 readLine을 이용한다.

```kotlin
val value: String? = readLine()
val value: String = readLine()!!
val number = readLine()!!.toInt()
```

## [Object declarations](https://kotlinlang.org/docs/object-declarations.html#object-declarations-overview)

- objcect declarations 와 일반 class 의 차이는 무엇일까?

## 유지보수하기 쉬운 패키지 명칭 활용

- step1, step2, step3 는 미션을 진행하는 순서일 뿐, 추후 유지보수시 도움을 주는 패키지명은 아니다.
- 클래스, 메서드, 변수와 마찬가지로 고민을 하고 적절한 패키지 이름을 부여해보자.
