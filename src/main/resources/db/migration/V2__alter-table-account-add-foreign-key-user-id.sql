alter table account add constraint fk_account_user foreign key (user_id) references users (id);