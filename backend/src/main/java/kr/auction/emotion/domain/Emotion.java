package kr.auction.emotion.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.auction.BaseEntity;
import kr.auction.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Emotion extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;
	private int myScore;

	private String aiFeedback;
	private int aiScore;

	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public Emotion(String content, int myScore, String aiFeedback, int aiScore, User user) {
		this.content = content;
		this.myScore = myScore;
		this.aiFeedback = aiFeedback;
		this.aiScore = aiScore;
		this.user = user;
	}

	public Emotion(String content, int myScore, User user) {
		this.content = content;
		this.myScore = myScore;
		this.user = user;
	}

	public void updateAiFeedback(String aiFeedback, int aiScore) {
		this.aiFeedback = aiFeedback;
		this.aiScore = aiScore;
	}
}
