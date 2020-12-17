create table user (uid integer not null auto_increment, avatar_url varchar(255), email varchar(255), exp integer, last_login_time datetime, nickname varchar(255), password varchar(255), primary key (uid)) engine=InnoDB;
create table article (aid integer not null auto_increment, author_id integer, click_amount integer, create_time datetime, last_edit_time datetime, status integer, text varchar(255), title varchar(255), primary key (aid)) engine=InnoDB;
create table archive (archive_id integer not null auto_increment, archive_name varchar(255), article_amount integer, primary key (archive_id)) engine=InnoDB;
create table tag (tag_id integer not null auto_increment, article_amount integer, tag_color varchar(255), tag_icon varchar(255), tag_name varchar(255), primary key (tag_id)) engine=InnoDB;
create table archive_article (archive_article_id integer not null auto_increment, archive_id integer, article_id integer, primary key (archive_article_id)) engine=InnoDB;
create table tag_article (tag_article_id integer not null auto_increment, article_id integer, tag_id integer, primary key (tag_article_id)) engine=InnoDB
