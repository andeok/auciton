import kakaoButton from './assets/kakao_login_medium_narrow.png'
import './App.css'

function App() {

    const handleClickKakao = () => {
        window.location.href = 'https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=af427fd32eb42c0f2ea5f9d99b383b6b&redirect_uri=http://localhost:8080/oauth2/login/kakao';
    }
    return (
        <>
            <button color={'primary'} onClick={handleClickKakao}/>
        </>
    )
}

export default App
