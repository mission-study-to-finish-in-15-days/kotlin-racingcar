# kotlin-racingcar


## 2단계 - 문자열 계산기

### 구현목록 
1. [x] 덧셈
2. [x] 뺄셈
3. [x] 곱셈
4. [x] 나눗셈
5. [x] 사칙 연산을 모두 포함하는 연산
6. [x] 입력값이 null 또는 빈 문자열일 경우 IllegalArgumentException throw
7. [x] 사칙연산 기호가 아닌 경우 IllegalArgumentException throw


## 3단계 - 자동차 경주

### 구현목록
1. [x] 사용자는 몇 대의 자동차로 몇 번의 이동을 할 것인지를 입력할 수 있어야 한다.
2. [x] 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다.
3. [x] 전진하는 조건은 0에서 9 사이에서 무작위 값을 구한 후 무작위 값이 4 이상일 경우이다.
4. [x] 자동차의 상태를 화면에 출력한다. 어느 시점에 출력할 것인지에 대한 제약은 없다.

### 프로그래밍 요구 사항
1. [x] 모든 로직에 단위 테스트를 구현한다. 단, UI(println 등) 로직은 제외
2. [x] 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
3. [x] UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.


## 4단계 - 자동차 경주(우승자)

### 구현목록
1. [x] 각 자동차에 이름을 부여할 수 있다. 자동차 이름은 5자를 초과할 수 없다.
2. [x] 전진하는 자동차를 출력할 때 자동차 이름을 같이 출력한다. 
3. [x] 자동차 이름은 쉼표(,)를 기준으로 구분한다. 
4. [ ] 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한 명 이상일 수 있다.

### 프로그래밍 요구 사항
1. [ ] 함수(또는 메소드)의 길이가 10라인을 넘어가지 않도록 구현한다.
