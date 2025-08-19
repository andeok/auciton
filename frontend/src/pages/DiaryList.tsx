import { useState } from "react";
import { DiaryEntry } from "./DiaryEntry";
import { Button } from "../components/ui/button";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "../components/ui/dialog";
import { Input } from "../components/ui/input";
import { Textarea } from "../components/ui/textarea";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "../components/ui/select";
import { Plus } from "lucide-react";

interface DiaryComment {
  id: string;
  author: string;
  content: string;
  timestamp: string;
}

interface DiaryEntryData {
  id: string;
  time: string;
  emotion: string;
  title: string;
  content: string;
  emotionColor: string;
  date: string;
  comment?: DiaryComment;
}

interface User {
  email: string;
  name: string;
}

interface DiaryListProps {
  selectedDate: Date;
  user: User | null;
  onLoginRequired: () => boolean;
}

const emotions = [
  { name: "행복", color: "#f59e0b" },
  { name: "슬픔", color: "#3b82f6" },
  { name: "화남", color: "#ef4444" },
  { name: "평온", color: "#10b981" },
  { name: "불안", color: "#8b5cf6" },
  { name: "기쁨", color: "#f97316" },
  { name: "우울", color: "#6b7280" },
  { name: "감사", color: "#ec4899" }
];

export function DiaryList({ selectedDate, user, onLoginRequired }: DiaryListProps) {
  const [entries, setEntries] = useState<DiaryEntryData[]>([
    {
      id: "1",
      time: "09:30",
      emotion: "행복",
      title: "좋은 아침",
      content: "오늘 아침에 커피를 마시며 창밖을 바라보니 기분이 너무 좋았다. 새로운 하루가 시작된다는 것만으로도 감사한 마음이 든다.",
      emotionColor: "#f59e0b",
      date: "2025-08-19"
    },
    {
      id: "2",
      time: "14:15",
      emotion: "평온",
      title: "점심 후 산책",
      content: "점심을 먹고 근처 공원을 산책했다. 바람이 시원하고 나무들이 푸르러서 마음이 평온해졌다.",
      emotionColor: "#10b981",
      date: "2025-08-19"
    },
    {
      id: "3",
      time: "20:45",
      emotion: "감사",
      title: "하루 마무리",
      content: "오늘 하루도 무사히 지나갔다. 가족들과 함께한 저녁시간이 특히 즐거웠다. 작은 일상에 감사하다.",
      emotionColor: "#ec4899",
      date: "2025-08-19"
    }
  ]);

  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [newEntry, setNewEntry] = useState({
    emotion: "",
    title: "",
    content: ""
  });

  const selectedDateString = selectedDate.toISOString().split('T')[0];
  const todayEntries = entries.filter(entry => entry.date === selectedDateString);

  const handleNewEntryClick = () => {
    if (onLoginRequired()) {
      setIsDialogOpen(true);
    }
  };

  const handleAddEntry = () => {
    if (!newEntry.emotion || !newEntry.content) return;

    const emotion = emotions.find(e => e.name === newEntry.emotion);
    const now = new Date();

    const entry: DiaryEntryData = {
      id: Date.now().toString(),
      time: now.toTimeString().slice(0, 5),
      emotion: newEntry.emotion,
      title: newEntry.title,
      content: newEntry.content,
      emotionColor: emotion?.color || "#6b7280",
      date: selectedDateString
    };

    setEntries([...entries, entry]);
    setNewEntry({ emotion: "", title: "", content: "" });
    setIsDialogOpen(false);
  };

  return (
      <div className="flex-1 p-4 space-y-4">
        <div className="flex items-center justify-between">
          <h2>오늘의 감정 일기</h2>
          <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
            <DialogTrigger asChild>
              <Button onClick={handleNewEntryClick} className="flex items-center gap-2">
                <Plus className="h-4 w-4" />
                새 일기 쓰기
              </Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-md">
              <DialogHeader>
                <DialogTitle>새 감정 일기 작성</DialogTitle>
              </DialogHeader>
              <div className="space-y-4">
                <div>
                  <label className="block mb-2">감정</label>
                  <Select value={newEntry.emotion} onValueChange={(value) => setNewEntry({...newEntry, emotion: value})}>
                    <SelectTrigger>
                      <SelectValue placeholder="감정을 선택하세요" />
                    </SelectTrigger>
                    <SelectContent>
                      {emotions.map((emotion) => (
                          <SelectItem key={emotion.name} value={emotion.name}>
                            <div className="flex items-center gap-2">
                              <div
                                  className="w-3 h-3 rounded-full"
                                  style={{ backgroundColor: emotion.color }}
                              />
                              {emotion.name}
                            </div>
                          </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>

                <div>
                  <label className="block mb-2">제목 (선택사항)</label>
                  <Input
                      value={newEntry.title}
                      onChange={(e) => setNewEntry({...newEntry, title: e.target.value})}
                      placeholder="일기 제목을 입력하세요"
                  />
                </div>

                <div>
                  <label className="block mb-2">내용</label>
                  <Textarea
                      value={newEntry.content}
                      onChange={(e) => setNewEntry({...newEntry, content: e.target.value})}
                      placeholder="오늘의 감정과 생각을 자유롭게 적어보세요..."
                      className="min-h-24"
                  />
                </div>

                <div className="flex gap-2 pt-4">
                  <Button variant="outline" className="flex-1" onClick={() => setIsDialogOpen(false)}>
                    취소
                  </Button>
                  <Button
                      className="flex-1"
                      onClick={handleAddEntry}
                      disabled={!newEntry.emotion || !newEntry.content}
                  >
                    저장
                  </Button>
                </div>
              </div>
            </DialogContent>
          </Dialog>
        </div>

        {todayEntries.length === 0 ? (
            <div className="text-center py-12 text-muted-foreground">
              <p>아직 작성된 일기가 없습니다.</p>
              <p className="mt-2">새로운 감정 일기를 작성해보세요!</p>
            </div>
        ) : (
            <div className="space-y-4">
              {todayEntries.map((entry) => (
                  <DiaryEntry key={entry.id} entry={entry} />
              ))}
            </div>
        )}
      </div>
  );
}