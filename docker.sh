
PROJECT_NAME="theaketing"
# 이전 이미지 삭제
IMAGE_NAME="183.106.75.68:5000/$PROJECT_NAME"
if [ "$(docker images -q $IMAGE_NAME)" ]; then
    docker rmi $IMAGE_NAME
    echo "이전 이미지 $IMAGE_NAME 삭제 완료"
else
    echo "이전 이미지 $IMAGE_NAME 없음"
fi

if [ "$(docker images -q $PROJECT_NAME)" ]; then
    docker rmi $PROJECT_NAME
    echo "이전 이미지 $PROJECT_NAME 삭제 완료"
else
    echo "이전 이미지 $PROJECT_NAME 없음"
fi


docker build --no-cache --platform linux/amd64 -t 183.106.75.68:5000/$PROJECT_NAME .

docker push 183.106.75.68:5000/$PROJECT_NAME
