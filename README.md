# takealook-android

## Getting Started

### Submodule 초기화

이 프로젝트는 `takealook-taskmanager`를 서브모듈로 사용하고 있습니다. 프로젝트를 처음 클론하거나 서브모듈을 초기화하려면 다음 명령어들을 실행해주세요:

```bash
# 저장소 클론 (처음 클론하는 경우)
git clone --recurse-submodules https://github.com/takea-look/takealook-android.git

# 또는 이미 클론한 경우
cd takealook-android
git submodule init
git submodule update --init --recursive
```

### 서브모듈 업데이트

서브모듈의 최신 변경사항을 가져오려면:

```bash
git submodule update --remote --recursive
```