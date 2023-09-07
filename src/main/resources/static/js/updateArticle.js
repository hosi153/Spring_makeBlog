const updateButton = document.getElementById('update-btn');

if (updateButton) {
    let id = document.getElementById('articleId').value;
    updateButton.addEventListener('click', event => {


        fetch('/api/articles/${article-id}/update', { //fetch 백엔드에 리퀘스트
            method: 'PATCH',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                articleTitle: document.getElementById('articleTitle').value, //키:밸류
                email: document.getElementById('email').value,
                userName: document.getElementById('userName').value,
                password: document.getElementById('password').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert('수정 완료되었습니다.'); //팝업
                location.replace('/articles/${article-id}'); //다른 주소를 호출하겠다 ( 현재는 전체 목록으로 호출 )
            });
    });
}