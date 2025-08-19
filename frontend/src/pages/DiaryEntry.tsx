import { Bot, Heart } from "lucide-react";
import {Card, CardContent, CardHeader} from "../components/ui/card.tsx";
import {Badge} from "../components/ui/badge.tsx";

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
  comment?: DiaryComment;
}

interface DiaryEntryProps {
  entry: DiaryEntryData;
}

const generateAIComment = (emotion: string, content: string): DiaryComment => {
  const comments = {
    "행복": [
      "정말 따뜻한 하루를 보내셨네요! 이런 소소한 행복이 일상을 더욱 빛나게 만드는 것 같아요. ✨",
      "기쁜 마음이 글에서 느껴져요. 이런 긍정적인 에너지가 계속 이어지길 바라요! 😊"
    ],
    "슬픔": [
      "힘든 시간을 보내고 계시는군요. 감정을 솔직하게 표현하는 것도 중요한 과정이에요. 천천히 괜찮아질 거예요. 💙",
      "슬픈 마음을 일기로 털어놓는 것만으로도 용기 있는 일이에요. 마음이 조금이라도 가벼워졌기를 바라요."
    ],
    "화남": [
      "화가 나는 일이 있으셨군요. 감정을 표현하고 정리하는 것이 중요해요. 깊게 숨을 쉬고 차근차근 해결해 나가세요. 🌱",
      "분노는 자연스러운 감정이에요. 일기에 써내려가며 마음이 조금 정리되셨기를 바라요."
    ],
    "평온": [
      "평온한 마음이 글에서 느껴져요. 이런 고요한 순간들이 얼마나 소중한지 느껴집니다. 🍃",
      "마음의 평화를 찾으신 것 같아 다행이에요. 이런 평온한 시간이 더 많아지길 바라요."
    ],
    "불안": [
      "불안한 마음이 드시는군요. 혼자가 아니에요, 이런 감정도 충분히 이해할 수 있어요. 한 걸음씩 나아가면 돼요. 🤗",
      "불안감을 일기로 표현하신 것만으로도 큰 의미가 있어요. 마음이 조금씩 안정되길 바라요."
    ],
    "기쁨": [
      "기쁜 소식이 있으셨나봐요! 이런 즐거운 순간들이 더 많이 찾아오길 바라요. 🎉",
      "기쁨이 가득한 하루를 보내셨네요. 이런 긍정적인 에너지가 계속 이어지길 응원해요!"
    ],
    "우울": [
      "우울한 기분이 드시는군요. 이런 감정도 자연스러운 거예요. 천천히, 하나씩 극복해 나가시길 응원할게요. 💜",
      "힘든 시간이지만 일기를 쓰며 감정을 정리하는 것 자체가 치유의 시작이에요."
    ],
    "감사": [
      "감사한 마음이 가득하신 하루였네요. 이런 마음가짐이 정말 아름다워요. 더 많은 좋은 일들이 찾아오길! 🙏",
      "작은 일상에 감사함을 느끼시는 모습이 정말 인상적이에요. 이런 마음이 더 큰 행복을 불러올 거예요."
    ]
  };

  const emotionComments = comments[emotion as keyof typeof comments] || [
    "오늘의 감정을 솔직하게 기록해주셔서 감사해요. 매일매일이 소중한 성장의 기록이 되고 있어요. 🌟"
  ];

  const randomComment = emotionComments[Math.floor(Math.random() * emotionComments.length)];

  return {
    id: `comment-${Date.now()}`,
    author: "감정 도우미",
    content: randomComment
  };
};

export function DiaryEntry({ entry }: DiaryEntryProps) {
  // 일기에 댓글이 없으면 AI 댓글을 생성
  const comment = entry.comment || generateAIComment(entry.emotion, entry.content);

  return (
      <Card className="mb-4">
        <CardHeader className="pb-3">
          <div className="flex items-center justify-between">
            <div className="flex items-center gap-2">
              <Badge
                  variant="secondary"
                  className="px-3 py-1"
                  style={{ backgroundColor: entry.emotionColor + '20', color: entry.emotionColor }}
              >
                {entry.emotion}
              </Badge>
              <span className="text-sm text-muted-foreground">{entry.time}</span>
            </div>
          </div>
          {entry.title && <h3 className="mt-2">{entry.title}</h3>}
        </CardHeader>
        <CardContent className="space-y-4">
          <p className="text-foreground leading-relaxed">{entry.content}</p>

          {/* AI 댓글 섹션 */}
          <div className="bg-muted/30 rounded-lg p-4 border-l-4 border-primary/20">
            <div className="flex items-start gap-3">
              <div className="flex-shrink-0">
                <div className="w-8 h-8 bg-primary/10 rounded-full flex items-center justify-center">
                  <Bot className="w-4 h-4 text-primary" />
                </div>
              </div>
              <div className="flex-1 space-y-1">
                <div className="flex items-center gap-2">
                  <span className="text-sm font-medium text-primary">{comment.author}</span>
                  <Heart className="w-3 h-3 text-red-400 fill-red-400" />
                </div>
                <p className="text-sm text-foreground/80 leading-relaxed">{comment.content}</p>
              </div>
            </div>
          </div>
        </CardContent>
      </Card>
  );
}