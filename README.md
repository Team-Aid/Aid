# Aid
병원, 약국 지도 앱

## 정보

- 타겟: Android

## 구조

- Clean architecture 기반
- [참고 링크](https://meetup.nhncloud.com/posts/345)
- 데이터 흐름: data -> domain -> presentation

```text
aid
+- data: 로컬/외부 DB 담당
|  +- data_sources: 실제 데이터 입출력 실행
|  +- mappers: Entity <-> Model 변환
|  +- repositories_impl: domain/repositories 구현
+- domain: 비즈니스 로직 담당, data랑 presentation 연결
|  +- models: 앱 내부용 (i.e view용) 데이터 표현
|  +- repositories: 데이터 관련 연산들
|  +- use_cases: presenter에 있기엔 복잡한 로직들
+- presentation: View 담당
|  +- views: View
|  +- presenters: ViewModel
```