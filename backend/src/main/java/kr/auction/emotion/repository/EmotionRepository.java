package kr.auction.emotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.auction.emotion.domain.Emotion;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {

}
