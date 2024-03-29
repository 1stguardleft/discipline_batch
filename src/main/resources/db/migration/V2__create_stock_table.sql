CREATE TABLE IF NOT EXISTS `stock` (
  `code` VARCHAR(6) NOT NULL COMMENT '종목코드',
  `name` VARCHAR(200) NOT NULL COMMENT '종목명',
  `market_code` VARCHAR(2) NOT NULL COMMENT '시장코드',
  `listed_date` TIMESTAMP(6) NOT NULL COMMENT '상장일',
  `delisted_date` TIMESTAMP(6) DEFAULT NULL COMMENT '상장폐지일',
  `creator_id` VARCHAR(40) NOT NULL COMMENT '생성자ID',
  `create_datetime` TIMESTAMP(6) NOT NULL COMMENT '생성일시',
  PRIMARY KEY (`code`)
) COMMENT '주식종목';

CREATE TABLE IF NOT EXISTS `stock_price_history` (
  `market_type` VARCHAR(2) NOT NULL COMMENT '시장구분',
  `code` VARCHAR(6) NOT NULL COMMENT '종목코드',
  `date` DATE NOT NULL COMMENT '일자',
  `open_price` BIGINT UNSIGNED NOT NULL COMMENT '시가',
  `high_price` BIGINT UNSIGNED NOT NULL COMMENT '고가',
  `low_price` BIGINT UNSIGNED NOT NULL COMMENT '저가',
  `close_price` BIGINT UNSIGNED NOT NULL COMMENT '종가',
  `creator_id` VARCHAR(40) NOT NULL COMMENT '생성자ID',
  `create_datetime` TIMESTAMP(6) NOT NULL COMMENT '생성일시',
  `modifier_id` BINARY(16) DEFAULT NULL COMMENT '수정자ID',
  `modify_datetime` TIMESTAMP(6) NOT NULL COMMENT '수정일시',
  PRIMARY KEY (`market_type`, `code`, `date`)
) COMMENT '주식가격이력';