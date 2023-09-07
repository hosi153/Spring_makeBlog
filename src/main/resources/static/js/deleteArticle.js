const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    let id = document.getElementById('articleId').value;
    deleteButton.addEventListener('click', event => {


        fetch('/api/articles/{article-id}/delete', { //fetch 백엔드에 리퀘스트
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json",
            }
            ,
            body: JSON.stringify({
                    articleTitle: document.getElementById('articleTitle').value, //키:밸류
                    email: document.getElementById('email').value,
                    userName: document.getElementById('userName').value,
                    password: document.getElementById('password').value,
                    content: document.getElementById('content').value
            }
            )
        })
            .then(() => {
                alert('삭제 완료되었습니다.'); //팝업
                // location.href='/members'; //다른 주소를 호출하겠다 ( 현재는 전체 목록으로 호출 )
                location.replace('/articles');
            });
    });
}