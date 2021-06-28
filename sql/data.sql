-- data for companies
insert into company("name", founder, foundation_date) values('Facebook', 'Mark Zuckerberg', '2004-02-04');
insert into company("name", founder, foundation_date) values('Google', 'Larry Page', '1998-09-04');

-- data for web sites
insert into web_site(id_company, "name", category, description)
values(1, 'Facebook', 'social network', 'Is an American online social media and social networking service owned by Facebook, Inc.');
insert into web_site(id_company, "name", category, description)
values(1, 'Instagram', 'social network', 'Is an American online social media and social networking service owned by Facebook, Inc.');
insert into web_site(id_company, "name", category, description)
values(1, 'Whatsapp', 'services', 'Is an American chat service owned by Facebook, Inc.');
insert into web_site(id_company, "name", category, description)
values(2, 'Gmail', 'services', 'Is an American email service owned by Google, Inc.');
insert into web_site(id_company, "name", category, description)
values(2, 'Youtube', 'streaming', 'Is an American online streaming service owned by Google, Inc.');
insert into web_site(id_company, "name", category, description)
values(2, 'Goggle Cloud Platform', 'cloud computing', 'Is an American cloud computing service owned by Google, Inc.')