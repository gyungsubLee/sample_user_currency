// user
CREATE TABLE user (
                          id INT PRIMARY KEY AUTO_INCREMENT COMMENT '고객 고유 식별자',
                          email VARCHAR(255) NOT NULL COMMENT '고객 이메일',
                          name VARCHAR(100) NOT NULL COMMENT '고객 이름',
                          created_at DATETIME(6) NOT NULL COMMENT '생성일자',
                          modified_at DATETIME(6) NOT NULL COMMENT '수정일자'
);

// 고객 데이터 삽입
INSERT INTO user (id, email, name, created_at, modified_at)
VALUES (1, 'test@naver.com', 'park', '2024-11-18 16:42:03.000000', '2024-11-18 16:42:03.000000');



// currency
CREATE TABLE currency (
                          id INT PRIMARY KEY AUTO_INCREMENT COMMENT '통화 고유 식별자',
                          exchange_rate DECIMAL(10, 2) NOT NULL COMMENT '환율',
                          currency_name CHAR(3) NOT NULL COMMENT '통화 이름', -- 수정된 부분
                          symbol CHAR(1) NOT NULL COMMENT '표시',
                          created_at DATETIME(6) NOT NULL COMMENT '생성일자',
                          modified_at DATETIME(6) NOT NULL COMMENT '수정일자'
);


// 통화 데이터 삽입
INSERT INTO currency (id, exchange_rate, currency_name, symbol, created_at, modified_at)
VALUES (1, 1430.00, 'USD', '$', '2024-11-18 16:42:03.000000','2024-11-18 16:42:03.000000');



// 중간 테이블
CREATE TABLE user_currency (
                                  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '환전 요청 고유 식별자',
                                  user_id INT NOT NULL COMMENT '고객 고유 식별자 (User 테이블 ID)',
                                  to_currency_id INT NOT NULL COMMENT '환전 대상 통화 식별자 (Currency 테이블 ID)',
                                  amount_in_krw DECIMAL(15, 2) NOT NULL COMMENT '환전 전 금액 (원화 기준)',
                                  amount_after_exchange DECIMAL(15, 2) NOT NULL COMMENT '환전 후 금액',
                                  status VARCHAR(20) DEFAULT 'normal' COMMENT '상태',
                                  created_at DATETIME(6) NOT NULL COMMENT '생성일자',
                                  modified_at DATETIME(6) NOT NULL COMMENT '수정일자',
                                  FOREIGN KEY (user_id) REFERENCES user(id),
                                  FOREIGN KEY (to_currency_id) REFERENCES currency(id)
);


