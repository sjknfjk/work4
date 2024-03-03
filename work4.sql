DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  PASSWORD VARCHAR(255) NOT NULL,
  phonenum VARCHAR(255) DEFAULT NULL,
  email VARCHAR(255) DEFAULT NULL,
  avatar_url VARCHAR(255) DEFAULT NULL,
  intro VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;

INSERT INTO users(username,PASSWORD) VALUES ('jiuxia211','123456');

DROP TABLE IF EXISTS articles;

CREATE TABLE articles (
  id INT NOT NULL AUTO_INCREMENT,
  auther_id INT NOT NULL,
  cover_url VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  commentnum INT DEFAULT 0,
  clicknum INT DEFAULT 0,
  likenum INT DEFAULT 0,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;

INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('1','www.baidu.com','百度','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('2','www.baidu.com1','百度1','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('2','www.baidu.com2','百度2','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('2','www.baidu.com3','百度3','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('1','www.baidu.com4','百度4','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('2','www.baidu.com5','百度5','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('1','www.baidu.com6','百度6','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('1','www.baidu.com7','百度7','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('1','www.baidu.com8','百度8','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('1','www.baidu.com9','百度9','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('1','www.baidu.com10','百度10','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('1','www.baidu.com11','百度11','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('1','www.baidu.com12','百度12','jsaljdflkjakfjk1');
INSERT INTO articles(auther_id,cover_url,title,content) VALUES ('1','www.baidu.com13','百度13','jsaljdflkjakfjk1');

DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id INT NOT NULL AUTO_INCREMENT,
  auther_id INT NOT NULL,
  article_id INT DEFAULT -1,
  parent_id INT DEFAULT -1,
  likenum INT DEFAULT 0,
  child_count INT DEFAULT 0,
  content TEXT NOT NULL,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;

INSERT INTO comments(auther_id,article_id,parent_id,content)VALUES(1,1,-1,'文章的第一条评论');

DROP TABLE IF EXISTS likes;

CREATE TABLE likes (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  article_id INT NOT NULL,
  comment_id INT NOT NULL,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;
INSERT INTO likes(user_id,article_id,comment_id)VALUES(1,2,-1);
INSERT INTO likes(user_id,article_id,comment_id)VALUES(1,4,-1);
INSERT INTO likes(user_id,article_id,comment_id)VALUES(1,5,-1);
INSERT INTO likes(user_id,article_id,comment_id)VALUES(1,7,-1);
INSERT INTO likes(user_id,article_id,comment_id)VALUES(1,8,-1);
INSERT INTO likes(user_id,article_id,comment_id)VALUES(1,11,-1);