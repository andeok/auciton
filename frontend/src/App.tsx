import { useState } from "react";
import { DiaryHeader } from "./pages/DiaryHeader";
import { DiaryList } from "./pages/DiaryList";
import { LoginDialog } from "./pages/LoginDialog";
import { Button } from "./components/ui/button";
import { User, LogOut } from "lucide-react";

interface User {
  email: string;
  name: string;
}

export default function App() {
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [user, setUser] = useState<User | null>(null);
  const [isLoginDialogOpen, setIsLoginDialogOpen] = useState(false);

  const handleLogin = (email: string, password: string) => {
    // 실제 앱에서는 여기에 실제 로그인 로직이 들어갑니다
    // 지금은 간단히 사용자 생성
    const newUser: User = {
      email,
      name: email.split('@')[0] // 이메일에서 이름 부분만 추출
    };
    setUser(newUser);
    setIsLoginDialogOpen(false);
  };

  const handleLogout = () => {
    setUser(null);
  };

  const handleLoginRequired = () => {
    if (!user) {
      setIsLoginDialogOpen(true);
      return false;
    }
    return true;
  };

  return (
      <div className="min-h-screen bg-background">
        <div className="max-w-2xl mx-auto bg-card min-h-screen shadow-sm">
          {/* 상단 헤더에 로그인 정보 추가 */}
          <div className="flex items-center justify-between p-4 border-b bg-card">
            <DiaryHeader
                selectedDate={selectedDate}
                onDateChange={setSelectedDate}
            />
            <div className="absolute right-4 top-4">
              {user ? (
                  <div className="flex items-center gap-2">
                    <span className="text-sm text-muted-foreground">안녕하세요, {user.name}님</span>
                    <Button variant="ghost" size="sm" onClick={handleLogout}>
                      <LogOut className="h-4 w-4" />
                    </Button>
                  </div>
              ) : (
                  <Button variant="ghost" size="sm" onClick={() => setIsLoginDialogOpen(true)}>
                    <User className="h-4 w-4 mr-2" />
                    로그인
                  </Button>
              )}
            </div>
          </div>

          <DiaryList
              selectedDate={selectedDate}
              user={user}
              onLoginRequired={handleLoginRequired}
          />

          <LoginDialog
              isOpen={isLoginDialogOpen}
              onOpenChange={setIsLoginDialogOpen}
              onLogin={handleLogin}
          />
        </div>
      </div>
  );
}