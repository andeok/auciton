import './App.css'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./LoginPage.tsx";
import KakaoRedirectPage from "./KakaoRedirectPage.tsx";

function App() {

    return (
        <div className='App'>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<LoginPage/>}></Route>
                    <Route path="/oauth/redirected/kakao" element={<KakaoRedirectPage />}></Route>
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default App
