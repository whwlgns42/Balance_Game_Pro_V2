<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
    <!-- 클래식 에디터 -->
    <script src="https://cdn.ckeditor.com/ckeditor5/29.1.0/classic/ckeditor.js"></script>
    <title>CKEditor Popup</title>
    
    <!-- 넓이 높이 조절 -->
    <style>
    body {
        background-color: black; /* 배경색을 검은색으로 설정 */
        color: white; /* 텍스트 색상을 흰색으로 설정 */
    }
    .ck.ck-editor {
        max-width: 500px;
    }
    .ck-editor__editable {
        min-height: 300px;
    }
    /* 팝업 창 스타일 */
    #myModal {
        display: none; /* 처음에는 숨겨진 상태로 설정 */
        position: fixed; /* 화면에 고정된 위치에 표시 */
        top: 50px; /* 오른쪽 위로부터의 거리 */
        right: 50px; /* 오른쪽으로부터의 거리 */
        background-color: white; /* 배경색을 흰색으로 설정 */
        padding: 20px;
        border-radius: 10px;
        z-index: 1000; /* 다른 요소들보다 위에 표시되도록 설정 */
    }
    #myModal .closeButton {
        position: absolute; /* 위치를 절대 위치로 설정 */
        top: 10px; /* 위쪽으로부터의 거리 */
        right: 10px; /* 오른쪽으로부터의 거리 */
        cursor: pointer; /* 마우스 커서를 포인터로 변경하여 클릭 가능한 모양으로 설정 */
    }
    </style>
</head>
<body>
    <h3>게시판 작성</h3>
    <button id="popupButton">팝업 열기</button>
    <div id="myModal" class="modal">
        <span class="closeButton" onclick="closeModal()">&times;</span> <!-- 모달 닫기 버튼 -->
        <div id="classic">
            <p>This is some sample content.</p>
        </div>
        <button id="submitButton">전송</button>
    </div>

    <script>
        var modalOpen = false; // 팝업 상태 변수 초기화
        var editor = null; // 에디터 객체 변수 초기화

        document.getElementById('popupButton').addEventListener('click', function() {
            if (!modalOpen) { // 팝업이 열려있지 않은 경우에만 열기
                document.getElementById('myModal').style.display = 'block'; // 모달 표시
                if (!editor) { // 에디터 객체가 없는 경우에만 생성
                    editor = ClassicEditor
                        .create( document.querySelector( '#classic' ))
                        .catch( error => {
                            console.error( error );
                        } );
                }
                modalOpen = true; // 팝업 상태 변경
            }
        });

        function closeModal() {
            document.getElementById('myModal').style.display = 'none'; // 모달 숨김
            modalOpen = false; // 팝업 상태 변경
            if (editor) { // 에디터 객체가 있는 경우에만 초기화
                editor.destroy().then( () => {
                    editor = null; // 에디터 객체 초기화
                } );
            }
        }

        document.getElementById('submitButton').addEventListener('click', function() {
            var content = editor.getData(); // 에디터의 내용을 가져옴
            // content를 서버로 전송하는 코드 작성
            console.log(content); // 예시로 콘솔에 출력
        });
    </script>
</body>
</html>