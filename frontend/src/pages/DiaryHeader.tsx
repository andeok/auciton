import {Button } from "../components/ui/button";
import { ChevronLeft, ChevronRight } from "lucide-react";

interface DiaryHeaderProps {
  selectedDate: Date;
  onDateChange: (date: Date) => void;
}

export function DiaryHeader({ selectedDate, onDateChange }: DiaryHeaderProps) {
  const goToPreviousDay = () => {
    const prevDay = new Date(selectedDate);
    prevDay.setDate(prevDay.getDate() - 1);
    onDateChange(prevDay);
  };

  const goToNextDay = () => {
    const nextDay = new Date(selectedDate);
    nextDay.setDate(nextDay.getDate() + 1);
    onDateChange(nextDay);
  };

  const formatDate = (date: Date) => {
    return date.toLocaleDateString('ko-KR', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      weekday: 'long'
    });
  };

  return (
      <div className="flex items-center gap-4 flex-1">
        <Button variant="ghost" size="icon" onClick={goToPreviousDay}>
          <ChevronLeft className="h-5 w-5" />
        </Button>

        <div className="flex-1 text-center">
          <h1>{formatDate(selectedDate)}</h1>
        </div>

        <Button variant="ghost" size="icon" onClick={goToNextDay}>
          <ChevronRight className="h-5 w-5" />
        </Button>
      </div>
  );
}