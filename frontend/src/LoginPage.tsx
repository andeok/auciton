export default function LoginPage() {

    const handleButtonClick = () => {
        window.location.href = 'https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=af427fd32eb42c0f2ea5f9d99b383b6b&redirect_uri=http://localhost:5173/oauth/redirected/kakao';
    };

    return (
        <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh'}}>
            <button
                onClick={handleButtonClick}
                style={{padding: '10px 20px', fontSize: '18px', borderRadius: '5px', cursor: 'pointer'}}
            >
                카카오톡 로그인
            </button>
        </div>
    );
}