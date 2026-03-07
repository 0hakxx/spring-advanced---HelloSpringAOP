# 🎯 Spring AOP 학습 프로젝트

> Inflearn 김영한 강사님의 Spring AOP(Aspect-Oriented Programming)의 핵심 개념부터 실무 응용까지 완벽하게 학습하는 프로젝트입니다.
> 
> **총 4개 모듈, 13개 테스트 클래스, 25개+ 테스트 메서드로 AOP의 모든 개념을 다룹니다.**

---

## 📋 목차

1. [프로젝트 개요](#-프로젝트-개요)
2. [프로젝트 구조](#-프로젝트-구조)
3. [모듈 1: 기본 AOP 구현](#-모듈-1-기본-aop-구현)
4. [모듈 2: 포인트컷 지정자](#-모듈-2-포인트컷-지정자-10가지)
5. [모듈 3: 실무 응용](#-모듈-3-실무-응용)
6. [모듈 4: AOP 함정](#-모듈-4-aop-함정--내부-호출-문제)
7. [빌드 및 실행](#-빌드-및-실행)

---

## 📌 프로젝트 개요

### 학습 목표
- ✅ Spring AOP의 핵심 개념 이해 (Pointcut, Advice, Aspect, JoinPoint)
- ✅ 10가지 포인트컷 지정자 마스터
- ✅ 5가지 Advice 타입 완벽 학습
- ✅ 실무에서 자주 사용되는 패턴 구현
- ✅ AOP의 함정(내부 호출 문제) 이해 및 해결

### 기술 스택
- **Spring Boot**: 3.5.11
- **Java**: 17
- **Spring AOP**: AspectJ 기반
- **Lombok**: 코드 간결화
- **JUnit 5**: 테스트 프레임워크
- **Gradle**: 빌드 도구

---

## 🗂️ 프로젝트 구조

```
src/main/java/hello/aop/
├── order/                           # 모듈 1: 기본 AOP 구현
│   ├── OrderService.java
│   ├── OrderRepository.java
│   └── aop/
│       ├── AspectV1.java
│       ├── AspectV2.java
│       ├── AspectV3.java
│       ├── AspectV4.java
│       ├── AspectV5.java
│       ├── AspectV6Advice.java
│       └── Pointcuts.java
│
├── member/                          # 모듈 2: 포인트컷 지정자 학습
│   ├── MemberService.java
│   ├── MemberServiceImpl.java
│   └── annotation/
│       ├── ClassAop.java
│       └── MethodAop.java
│
├── pointcut/                        # 모듈 2: 포인트컷 지정자 검증
│   ├── ExecutionTest.java
│   ├── WithinTest.java
│   ├── ArgsTest.java
│   ├── AtAnnotationTest.java
│   ├── AtTargetAtWithinTest.java
│   ├── ThisTargetTest.java
│   ├── BeanTest.java
│   └── ParameterTest.java
│
├── exam/                            # 모듈 3: 실무 응용
│   ├── ExamService.java
│   ├── ExamRepository.java
│   ├── ExamTest.java
│   ├── annotation/
│   │   ├── Trace.java
│   │   └── Retry.java
│   └── aop/
│       ├── TraceAspect.java
│       └── RetryAspect.java
│
└── internalcall/                    # 모듈 4: AOP 함정
    ├── CallServiceV0.java
    ├── CallServiceV1.java
    └── aop/
        └── CallLogAspect.java
```

---

## 🎓 모듈 1: 기본 AOP 구현

### 📖 개요
AOP의 기본 개념부터 Advice lifecycle까지 단계별로 진화하는 Aspect를 구현합니다.

### 🔄 Aspect 진화 단계

#### **AspectV1: 기본 @Around로 로깅**
- 가장 기본적인 형태, `@Around`만 사용

#### **AspectV2: @Pointcut 추상화**
- 포인트컷을 별도 메서드로 추상화, 여러 Advice에서 재사용 가능

#### **AspectV3: 다중 Pointcut + 트랜잭션**
- `&&`, `||`, `!` 연산자로 포인트컷 조합

#### **AspectV4: @Before 추가**
- 메서드 호출 **이전**에 실행

#### **AspectV5: After 계열 분리**
- `@AfterReturning`: 정상 반환 후
- `@AfterThrowing`: 예외 발생 후
- `@After`: 무조건 종료 시점

#### **AspectV6Advice: 완전한 Lifecycle**
- 모든 Advice 타입 통합: @Around, @Before, @AfterReturning, @AfterThrowing, @After

### 📊 Advice 실행 순서

```
메서드 호출 → @Around시작 → @Before → 메서드실행 → 
(@AfterReturning|@AfterThrowing) → @After → @Around종료
```

---

## 🎯 모듈 2: 포인트컷 지정자 (10가지)

### 🔍 포인트컷 지정자 요약

| # | 지정자 | 설명 | 사용 빈도 |
|---|--------|------|---------|
| 1️⃣ | `execution()` | 메서드 시그니처 기반 (정적) | ⭐⭐⭐⭐⭐ |
| 2️⃣ | `within()` | 타입/클래스 기반 | ⭐⭐⭐ |
| 3️⃣ | `args()` | 런타임 인수 타입 기반 (동적) | ⭐⭐ |
| 4️⃣ | `this()` | 프록시 타입 기반 | ⭐ |
| 5️⃣ | `target()` | 대상 객체 타입 기반 | ⭐⭐ |
| 6️⃣ | `@target()` | 대상 클래스 애노테이션 | ⭐⭐ |
| 7️⃣ | `@within()` | 타입 내 애노테이션 | ⭐⭐ |
| 8️⃣ | `@annotation()` | 메서드 애노테이션 | ⭐⭐⭐⭐ |
| 9️⃣ | `@args()` | 인수 애노테이션 | ⭐ |
| 🔟 | `bean()` | 빈 이름 기반 | ⭐⭐ |

### 💡 핵심 차이점

- **execution() vs args()**: 정적(컴파일) vs 동적(런타임)
- **@target() vs @within()**: 부모메서드포함 vs 자식메서드만
- **this() vs target()**: 프록시타입 vs 대상객체타입

---

## 🚀 모듈 3: 실무 응용 (커스텀 Annotation 기반 AOP)

### @Trace와 @Retry 구현

#### **@Trace**
- 메서드 호출 추적 및 로깅
- TraceAspect에서 @Before로 처리

#### **@Retry**
- value 속성으로 재시도 횟수 지정 (기본값=3)
- RetryAspect에서 @Around로 자동 재시도 처리

### 🔄 동작 예시
```
메서드 호출 → TraceAspect 로깅 → 메서드 실행
→ 예외발생 → RetryAspect catch → 최대 3회 재시도 → 성공/실패 반환
```

---

## ⚠️ 모듈 4: AOP 함정 - 내부 호출 문제

### 🔴 문제: CallServiceV0
```java
public void external() {
    internal();  // this.internal() 호출 (프록시 우회)
}
```
- external()는 AOP 적용 ✅
- internal()은 AOP 미적용 ❌ (같은 객체 직접 호출)

### 🟢 해결책: CallServiceV1
```java
private CallServiceV1 callServiceV1;  // 프록시 객체 주입

public void external() {
    callServiceV1.internal();  // 프록시를 통해 호출
}
```
- 모든 호출이 프록시를 거쳐 AOP 적용 ✅

### 🧠 핵심 개념
- Spring AOP는 프록시 기반 (내부 호출은 프록시를 거치지 않음)
- **해결책**: 프록시 객체 주입 후 호출

---

## 🧪 테스트 실행

```bash
# 전체 테스트
./gradlew test

# 특정 모듈
./gradlew test --tests hello.aop.pointcut.ExecutionTest
./gradlew test --tests hello.aop.exam.ExamTest
./gradlew test --tests hello.aop.internalcall.aop.*
```

---

## 🏗️ 빌드 및 실행

```bash
# 빌드
./gradlew build

# 실행
./gradlew bootRun

# 테스트 레포트 보기
open build/reports/tests/test/index.html
```

---

## 📊 프로젝트 통계

- 총 테스트 클래스: 13개
- 총 테스트 메서드: 25+개
- 총 Aspect 클래스: 8개
- 포인트컷 지정자: 10가지
- 커스텀 애노테이션: 5개

---

## 💡 마지막 팁

1. **순서대로 학습**: V1 → V2 → ... → V6
2. **테스트 로그 읽기**: 가장 효과적인 학습법
3. **10가지 포인트컷 완벽 이해**: 가장 중요한 부분
4. **내부 호출 함정 숙지**: 실무 버그의 원인이 될 수 있음

---

**Happy Learning! 🚀**
