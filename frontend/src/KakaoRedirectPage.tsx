import React, {useEffect} from 'react';
import {useLocation, useNavigate} from 'react-router-dom';
import axios from 'axios';

const KakaoRedirectPage = () => {
    const location = useLocation();
    const navigate = useNavigate();


// TODO : 여기부터 개발
    const handleOAuthKakao = async (code) => {
        try {
            // 카카오로부터 받아온 code를 서버에 전달하여 카카오로 회원가입 & 로그인한다
          const request = {
            code: code,
            redirectUri: "http://localhost:5173/oauth/redirected/kakao" // 카카오 로그인 후 리다이렉트할 URI
          }
            const response = await axios.post('http://localhost:8080/oauth2/login/kakao', request);
            const data = response.data; // 응답 데이터
            navigate("/success");
        } catch (error) {
            navigate("/fail");
        }
    };
    useEffect(() => {
        const searchParams = new URLSearchParams(location.search);
        const code = searchParams.get('code');  // 카카오는 Redirect 시키면서 code를 쿼리 스트링으로 준다.
        if (code) {
            handleOAuthKakao(code);
        }
    }, [location]);

    return (
        <div>
            <div>Processing...</div>
        </div>
    );
};

export default KakaoRedirectPage;
