-- =========================
-- ROLE
-- =========================
INSERT INTO role (role_code, role_name) VALUES
    ('ADMIN', 'Admin'),
    ('MPOS', 'MPOS'),
    ('KT', 'Accountant'),
    ('USER', 'User');

-- =========================
-- ACCOUNT
-- =========================
INSERT INTO account (account_code, username, password, phone_number, email, role_code) VALUES
    ('TK_USER01', 'devsoga', '$2a$10$c3h6fvOYaAcAS3BDYZWRG.7NHD8WePgNugJKU5RYCgMM.46Ho7ewq', '0934567890', 'user@email.com', 'USER'),
    ('TK_ADMIN', 'admin', '$2a$10$c3h6fvOYaAcAS3BDYZWRG.7NHD8WePgNugJKU5RYCgMM.46Ho7ewq', '0901234567', 'admin@bookstore.com', 'ADMIN'),
    ('TK_MPOS1', 'mpos1', '$2a$10$c3h6fvOYaAcAS3BDYZWRG.7NHD8WePgNugJKU5RYCgMM.46Ho7ewq', '0912345678', 'mpos1@bookstore.com', 'MPOS'),
    ('TK_MPOS2', 'mpos2', '$2a$10$c3h6fvOYaAcAS3BDYZWRG.7NHD8WePgNugJKU5RYCgMM.46Ho7ewq', '0912345679', 'mpos2@bookstore.com', 'MPOS'),
    ('TK_KETOAN', 'ketoan', '$2a$10$c3h6fvOYaAcAS3BDYZWRG.7NHD8WePgNugJKU5RYCgMM.46Ho7ewq', '0923456789', 'ketoan@bookstore.com', 'KT');

-- =========================
-- EMPLOYEE
-- =========================
INSERT INTO employee (employee_code, employee_name, date_of_birth, gender, status, account_code) VALUES
    ('NV_ADMIN', 'Admin', '1990-01-01', 1, 1, 'TK_ADMIN'),
    ('NV_MPOS1', 'MPOS1', '1995-05-15', 1, 1, 'TK_MPOS1'),
    ('NV_MPOS2', 'MPOS2', '1996-06-20', 1, 1, 'TK_MPOS2'),
    ('NV_KETOAN', 'KeToan', '1988-12-20', 1, 1, 'TK_KETOAN');


-- =========================
-- CUSTOMER TYPE
-- =========================
INSERT INTO customer_type (customer_type_code, customer_type_name, promotion_code, description) VALUES
('CUS_REGULAR', 'Regular Customer','', 'Basic membership level'),
('CUS_SILVER', 'Silver Customer', 'VIP5', 'Total spending above $200'),
('CUS_GOLD', 'Gold Customer', 'VIP7', 'Total spending above $1,000'),
('CUS_DIAMOND', 'Diamond Customer', 'VIP10', 'Total spending above $5,000');


-- =========================
-- CUSTOMER
-- =========================
INSERT INTO customer (customer_code, customer_name, points, address, customer_type_code, account_code) VALUES
    ('KH_USER', 'Đặng Khôi Nguyên', 100, '123 Đường ABC, TP.HCM', 'CUS_SILVER', 'TK_USER01');

-- =========================
-- SUPPLIER
-- =========================
INSERT INTO supplier (supplier_code, supplier_name, address, phone_number, email)
VALUES ('NCC01', 'NXB Tổng Hợp', '123 Đường Sách, HN', '0912345678', 'nxb@example.com');


-- =========================
-- PROMOTION TYPE & PROMOTION
-- =========================
-- Promotion types: percentage, fixed amount, free shipping
INSERT INTO promotion_type (promotion_type_code, promotion_type_name, description) VALUES
    ('PT_01', 'Percent','percentage'),
    ('PT_02', 'Amount','fixed amount');

-- Sample promotions
INSERT INTO promotion (promotion_code, promotion_name, description, value, promotion_type_code, status) VALUES
    ('VIP5', 'Silver Customer', 'Giảm 5% cho khách hàng Silver', 0.05, 'PT_01', 1),
    ('VIP7', 'Gold Customer', 'Giảm 7% cho khách hàng Gold', 0.07, 'PT_01', 1),
    ('VIP10', 'Diamond Customer', 'Giảm 10% cho khách hàng Diamond', 0.10, 'PT_01', 1),
    ('FA200', 'Happy New Year', 'Giảm 200.000đ', 200000, 'PT_02', 1),
    ('FA20', 'Noel', 'Giảm 20% cho đơn hàng trong dịp Noel', 0.20, 'PT_01', 1);


-- =========================
-- PRODUCT CATEGORY (20 CATEGORIES)
-- =========================
INSERT INTO product_category (category_code, category_name, category_type, description) VALUES
	('LSP01', 'Romance','book', 'Heartfelt love stories filled with passion, emotion, and unforgettable connections.'),
	('LSP02', 'Horror','book', 'Spine-chilling tales of fear, suspense, and supernatural terror.'),
	('LSP03', 'Fantasy','book', 'Epic adventures in magical worlds full of wonder, heroes, and imagination.'),
	('LSP04', 'Business','book', 'Insightful books on strategy, leadership, and success in the world of business.'),
	('LSP05', 'Drama','book', 'Emotional and powerful narratives exploring life, relationships, and human nature.'),
	('LSP06', 'Biography','book', 'Inspiring life stories of remarkable people who shaped history and culture.'),
	('LSP07', 'Cook','book', 'Essential guides and recipes for mastering the art of cooking.'),
	('LSP08', 'Poetry','book', 'Beautifully written verses that touch the heart and awaken the soul.'),
	('LSP09', 'Art','book', 'Books that celebrate creativity, design, and the beauty of artistic expression.'),
	('LSP10', 'Architecture','book', 'Explorations of architectural design, space, and the harmony of structure and art.'),
	('LSP11', 'Modelkit','modelKit', 'Highly detailed model kits for builders and collectors, combining creativity, craftsmanship, and precision engineering.'),
	('LSP12', 'Figure','figure', 'A wide range of collectible figures featuring iconic characters, crafted with exceptional detail and dynamic poses.'),
	('LSP13', 'Calculator','calculator', 'Smart and reliable Casio calculators designed for students and professionals, offering precision, durability, and advanced functionality.'),
	('LSP14', 'Note','note', 'Trendy and creative notebooks with unique designs, perfect for journaling, planning, and everyday note-taking.'),
	('LSP15', 'Watch','watch', 'Stylish and durable Casio watches that combine innovation, modern design, and timeless craftsmanship.'),
	('LSP16', 'Pen','pen', 'Premium pens that deliver smooth writing and elegant design — ideal for professionals, collectors, and creatives.'),
	('LSP17', 'Draw','draw', 'A collection of acrylic paints, markers, and brushes that inspire artists to explore color, texture, and creativity.'),
	('LSP18', 'Studentbook','studentBook', 'Fun and colorful student notebooks featuring cute designs and high-quality paper for everyday school use.'),
	('LSP19', 'CompaEke','compaEke', 'Practical geometry tools including compasses, rulers, and setsquares — perfect for students and technical drawing tasks.'),
	('LSP20', 'PencilEraser','pencilEraser', 'A variety of pencils, erasers, and sharpeners designed for both students and artists, combining functionality with fun design.');


	

-- =========================
-- PRODUCTS 
-- =========================

INSERT INTO product
(product_code, product_name, description, image, author, publisher, status, category_code, promotion_code)
VALUES

-- LSP01 Romance
	('SP0101','Regretting You',
	 'A touching story of a mother and daughter whose relationship falls apart after a tragedy. As secrets and misunderstandings grow, both struggle to rebuild love and trust.',
	 '/uploads/Romance/Romance1.jpg',
	 'Colleen Hoover','Montlake', TRUE, 'LSP01', NULL),
	
	('SP0102','Reminders of Him',
	 'After five years in prison, Kenna returns home hoping to reunite with her daughter. With everyone against her, she finds unexpected support from bar owner Ledger.',
	 '/uploads/Romance/Romance2.jpg',
	 'Colleen Hoover','Montlake', TRUE, 'LSP01', NULL),
	
	('SP0103','Broken Country (Reese''s Book Club)',
	 'When the past resurfaces, Beth is forced to face the man who once broke her heart. Secrets, grief, and love collide in this emotional small-town story.',
	 '/uploads/Romance/Romance3.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP01', NULL),
	
	('SP0104','The Great Alone',
	 'In 1970s Alaska, a family seeks a fresh start but finds hardship and danger instead. Amid the wilderness, young Leni learns about love, survival, and resilience.',
	 '/uploads/Romance/Romance5.jpg',
	 'Kristin Hannah','St. Martin''s Press', TRUE, 'LSP01', NULL),
	
	('SP0105','Verity',
	 'A struggling writer discovers a chilling manuscript that reveals the dark secrets of a bestselling author. Passion, obsession, and deception blur the line between truth and fiction.',
	 '/uploads/Romance/Romance4.jpg',
	 'Colleen Hoover','Atria Books', TRUE, 'LSP01', NULL),
	
	('SP0106','Quicksilver',
	 'Saeris hides her magical powers until an encounter with Death transports her to a frozen world ruled by the Fae. There, she must fight to survive and uncover her destiny.',
	 '/uploads/Romance/Romance6.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP01', NULL),
	
	('SP0107','The Very Secret Society of Irregular Witches',
	 'A lonely witch is invited to teach magic to three young girls at a mysterious house. There, she discovers friendship, belonging, and unexpected love.',
	 '/uploads/Romance/Romance7.jpg',
	 'Sangu Mandanna','Berkley', TRUE, 'LSP01', NULL),
	
	('SP0108','The Seven Husbands of Evelyn Hugo',
	 'Hollywood icon Evelyn Hugo recounts her glamorous yet scandalous life to a young journalist, revealing shocking truths about fame, love, and identity.',
	 '/uploads/Romance/Romance8.jpg',
	 'Taylor Jenkins Reid','Atria Books', TRUE, 'LSP01', NULL),
	
	('SP0109','How My Neighbor Stole Christmas',
	 'A grumpy man’s quiet holiday is ruined when his spirited neighbor drags him into a Christmas contest — and maybe, into love.',
	 '/uploads/Romance/Romance9.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP01', NULL),
	
	('SP0110','Skyshade (The Lightlark Saga Book 3)',
	 'As Isla faces the aftermath of war and heartbreak, she must choose between forbidden love and saving her world from eternal darkness.',
	 '/uploads/Romance/Romance10.jpg',
	 'Alex Aster','Amulet Books', TRUE, 'LSP01', NULL),



-- LSP02 Horror
	('SP0201','Harry Potter T1',
	 'A chilling Southern horror set in 1950s Florida. A young boy sent to a reform school must survive cruelty and ghosts haunting its halls.',
	 '/uploads/Horror/Horror1.jpg',
	 'J.K. Rowling','Bloomsbury', TRUE, 'LSP02', NULL),
	
	('SP0202','House of Leaves',
	 'A complex psychological horror about a mysterious house that defies space and logic, and the madness it brings to those who study it.',
	 '/uploads/Horror/Horror2.jpg',
	 'Mark Z. Danielewski','Pantheon', TRUE, 'LSP02', NULL),
	
	('SP0203','Frankenstein the Original 1818 Text',
	 'Mary Shelley’s gothic masterpiece about a scientist who creates life, only to be destroyed by his own creation and guilt.',
	 '/uploads/Horror/Horror3.jpg',
	 'Mary Shelley','Penguin Classics', TRUE, 'LSP02', NULL),
	
	('SP0204','Home Before Dark',
	 'A woman returns to her childhood home made famous by her father’s horror memoir, only to discover the terrifying truth behind it.',
	 '/uploads/Horror/Horror4.jpg',
	 'Riley Sager','Dutton', TRUE, 'LSP02', NULL),
	
	('SP0205','Phantasma',
	 'In a deadly haunted mansion game, Ophelia must survive traps and demons to save her sister — without falling for the charming stranger guiding her.',
	 '/uploads/Horror/Horror5.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0206','Never Flinch',
	 'A gripping Stephen King thriller weaving revenge, obsession, and morality as two killers’ paths collide in a deadly game of justice.',
	 '/uploads/Horror/Horror6.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0207','It',
	 'In the town of Derry, children face a terrifying entity that takes the form of their deepest fears — and returns decades later for revenge.',
	 '/uploads/Horror/Horror7.jpg',
	 'Stephen King','Viking Press', TRUE, 'LSP02', NULL),
	
	('SP0208','We Used to Live Here',
	 'A young couple’s dream home becomes a nightmare when strangers claiming to be its former residents refuse to leave. Reality soon unravels.',
	 '/uploads/Horror/Horror8.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0209','Roald Dahl Book of Ghost Stories',
	 'Fourteen eerie tales selected by Roald Dahl himself — classic ghost stories meant to unsettle, disturb, and chill you to the bone.',
	 '/uploads/Horror/Horror9.jpg',
	 'Roald Dahl (Editor)','Farrar, Straus and Giroux', TRUE, 'LSP02', NULL),
	
	('SP0210','The Haunting of Hill House',
	 'Four strangers explore a haunted mansion that preys on their fears. Shirley Jackson’s timeless tale of madness and the supernatural.',
	 '/uploads/Horror/Horror10.jpg',
	 'Shirley Jackson','Viking Press', TRUE, 'LSP02', NULL),
	
	-- SP0211 → SP0220
	('SP0211','Hazelthorn',
	 'A chilling Southern horror set in 1950s Florida. A young boy sent to a reform school must survive cruelty and ghosts haunting its halls.',
	 '/uploads/Horror/Horror11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0212','Girl Dinner',
	 'A complex psychological horror about a mysterious house that defies space and logic, and the madness it brings to those who study it.',
	 '/uploads/Horror/Horror12.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0213','King Sorrow',
	 'Mary Shelley’s gothic masterpiece about a scientist who creates life, only to be destroyed by his own creation and guilt.',
	 '/uploads/Horror/Horror13.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0214','The Haunting of Paynes Hollow',
	 'A woman returns to her childhood home made famous by her father’s horror memoir, only to discover the terrifying truth behind it.',
	 '/uploads/Horror/Horror14.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0215','Bog Queen',
	 'In a deadly haunted mansion game, Ophelia must survive traps and demons to save her sister — without falling for the charming stranger guiding her.',
	 '/uploads/Horror/Horror15.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0216','The Women of Wild Hill',
	 'A gripping Stephen King thriller weaving revenge, obsession, and morality as two killers’ paths collide in a deadly game of justice.',
	 '/uploads/Horror/Horror16.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0217','The Graceview Patient',
	 'In the town of Derry, children face a terrifying entity that takes the form of their deepest fears — and returns decades later for revenge.',
	 '/uploads/Horror/Horror17.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0218','The Hong Kong Widow',
	 'A young couple’s dream home becomes a nightmare when strangers claiming to be its former residents refuse to leave. Reality soon unravels.',
	 '/uploads/Horror/Horror18.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0219','Crafting for Sinners',
	 'Fourteen eerie tales selected by Roald Dahl himself — classic ghost stories meant to unsettle, disturb, and chill you to the bone.',
	 '/uploads/Horror/Horror19.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0220','Toms Crossing',
	 'Four strangers explore a haunted mansion that preys on their fears. Shirley Jackson’s timeless tale of madness and the supernatural.',
	 '/uploads/Horror/Horror20.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	-- SP0221 → SP0230
	('SP0221','Colin Gets Promoted and Dooms the World',
	 'A chilling Southern horror set in 1950s Florida. A young boy sent to a reform school must survive cruelty and ghosts haunting its halls.',
	 '/uploads/Horror/Horror21.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0222','The Last Witch',
	 'A complex psychological horror about a mysterious house that defies space and logic, and the madness it brings to those who study it.',
	 '/uploads/Horror/Horror22.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0223','The Salvage',
	 'Mary Shelley’s gothic masterpiece about a scientist who creates life, only to be destroyed by his own creation and guilt.',
	 '/uploads/Horror/Horror23.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0224','How to Fake a Haunting',
	 'A woman returns to her childhood home made famous by her father’s horror memoir, only to discover the terrifying truth behind it.',
	 '/uploads/Horror/Horror24.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0225','If the Dead Belong Here',
	 'In a deadly haunted mansion game, Ophelia must survive traps and demons to save her sister — without falling for the charming stranger guiding her.',
	 '/uploads/Horror/Horror25.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0226','Atlas of Unknowable Things',
	 'A gripping Stephen King thriller weaving revenge, obsession, and morality as two killers’ paths collide in a deadly game of justice.',
	 '/uploads/Horror/Horror26.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0227','Our Vicious Descent',
	 'In the town of Derry, children face a terrifying entity that takes the form of their deepest fears — and returns decades later for revenge.',
	 '/uploads/Horror/Horror27.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0228','Final Cut',
	 'A young couple’s dream home becomes a nightmare when strangers claiming to be its former residents refuse to leave. Reality soon unravels.',
	 '/uploads/Horror/Horror28.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0229','And the River Drags Her Down',
	 'Fourteen eerie tales selected by Roald Dahl himself — classic ghost stories meant to unsettle, disturb, and chill you to the bone.',
	 '/uploads/Horror/Horror29.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0230','The Midnight Knock',
	 'Four strangers explore a haunted mansion that preys on their fears. Shirley Jackson’s timeless tale of madness and the supernatural.',
	 '/uploads/Horror/Horror30.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	-- SP0231 → SP0240
	('SP0231','The Night That Finds Us All',
	 'A chilling Southern horror set in 1950s Florida. A young boy sent to a reform school must survive cruelty and ghosts haunting its halls.',
	 '/uploads/Horror/Horror31.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0232','The Works of Vermin',
	 'A complex psychological horror about a mysterious house that defies space and logic, and the madness it brings to those who study it.',
	 '/uploads/Horror/Horror32.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0233','Ill Quit When Im Dead',
	 'Mary Shelley’s gothic masterpiece about a scientist who creates life, only to be destroyed by his own creation and guilt.',
	 '/uploads/Horror/Horror33.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0234','The Unveiling',
	 'A woman returns to her childhood home made famous by her father’s horror memoir, only to discover the terrifying truth behind it.',
	 '/uploads/Horror/Horror34.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0235','Vampires at Sea',
	 'In a deadly haunted mansion game, Ophelia must survive traps and demons to save her sister — without falling for the charming stranger guiding her.',
	 '/uploads/Horror/Horror35.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0236','Slayers of Old',
	 'A gripping Stephen King thriller weaving revenge, obsession, and morality as two killers’ paths collide in a deadly game of justice.',
	 '/uploads/Horror/Horror36.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0237','Herculine',
	 'In the town of Derry, children face a terrifying entity that takes the form of their deepest fears — and returns decades later for revenge.',
	 '/uploads/Horror/Horror37.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0238','The Resurrectionist',
	 'A young couple’s dream home becomes a nightmare when strangers claiming to be its former residents refuse to leave. Reality soon unravels.',
	 '/uploads/Horror/Horror38.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0239','Psychopomp & Circumstance',
	 'Fourteen eerie tales selected by Roald Dahl himself — classic ghost stories meant to unsettle, disturb, and chill you to the bone.',
	 '/uploads/Horror/Horror39.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0240','Uncanny Valley Girls: Essays on Horror, Survival, and Love',
	 'Four strangers explore a haunted mansion that preys on their fears. Shirley Jackson’s timeless tale of madness and the supernatural.',
	 '/uploads/Horror/Horror40.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),


-- LSP03 Fantasy
	('SP0301','Dungeon Crawler Carl',
	 'A hilarious and inventive LitRPG adventure following Carl as he battles through a deadly dungeon after the apocalypse. Packed with humor, dark twists, and nonstop action.',
	 '/uploads/Fantasy/Fantasy1.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0302','The Hobbit: Illustrated Edition',
	 'J.R.R. Tolkien’s timeless tale of Bilbo Baggins and his unexpected journey through Middle-earth. A beautifully illustrated modern classic of adventure and courage.',
	 '/uploads/Fantasy/Fantasy2.jpg',
	 'J.R.R. Tolkien','HarperCollins', TRUE, 'LSP03', NULL),
	
	('SP0303','The Alchemist',
	 'Paulo Coelho’s modern classic about following your dreams and listening to your heart. A parable of self-discovery that has inspired millions worldwide.',
	 '/uploads/Fantasy/Fantasy3.jpg',
	 'Paulo Coelho','HarperOne', TRUE, 'LSP03', NULL),
	
	('SP0304','A Court of Thorns and Roses',
	 'A captivating romantic fantasy about Feyre, a huntress who becomes entangled in a dangerous faerie world after killing a wolf. Passion, magic, and peril intertwine in this spellbinding tale.',
	 '/uploads/Fantasy/Fantasy4.jpg',
	 'Sarah J. Maas','Bloomsbury', TRUE, 'LSP03', NULL),
	
	('SP0305','Throne of Glass',
	 'The beginning of Sarah J. Maas’s epic fantasy series. Celaena, a deadly assassin, is offered freedom if she can win a brutal competition to become the King’s Champion.',
	 '/uploads/Fantasy/Fantasy5.jpg',
	 'Sarah J. Maas','Bloomsbury', TRUE, 'LSP03', NULL),
	
	('SP0306','A Court of Silver Flames',
	 'Nesta Archeron must face her past and her fiery bond with Cassian while the Night Court faces new threats. A passionate and empowering fantasy of love, trauma, and redemption.',
	 '/uploads/Fantasy/Fantasy6.jpg',
	 'Sarah J. Maas','Bloomsbury', TRUE, 'LSP03', NULL),
	
	('SP0307','Queen of Shadows',
	 'Aelin Galathynius returns to her empire seeking vengeance and freedom. The fourth book in the Throne of Glass series delivers powerful action and emotional depth.',
	 '/uploads/Fantasy/Fantasy7.jpg',
	 'Sarah J. Maas','Bloomsbury', TRUE, 'LSP03', NULL),
	
	('SP0308','A Court of Frost and Starlight',
	 'A heartwarming companion to the ACOTAR series. Feyre and Rhys prepare for Winter Solstice as they heal from war and rebuild their court, balancing love and lingering scars.',
	 '/uploads/Fantasy/Fantasy8.jpg',
	 'Sarah J. Maas','Bloomsbury', TRUE, 'LSP03', NULL),
	
	('SP0309','A Court of Wings and Ruin',
	 'Feyre must play a deadly game of deception as war looms over Prythian. A breathtaking mix of love, betrayal, and sacrifice in the thrilling conclusion of the ACOTAR trilogy.',
	 '/uploads/Fantasy/Fantasy9.jpg',
	 'Sarah J. Maas','Bloomsbury', TRUE, 'LSP03', NULL),
	
	('SP0310','Empire of Storms',
	 'Aelin’s path to the throne becomes more perilous than ever as kingdoms fall into chaos. A gripping continuation of the Throne of Glass saga filled with war, magic, and destiny.',
	 '/uploads/Fantasy/Fantasy10.jpg',
	 'Sarah J. Maas','Bloomsbury', TRUE, 'LSP03', NULL),
	
	('SP0311','Alchemy of Secrets',
	 'A hilarious and inventive LitRPG adventure following Carl as he battles through a deadly dungeon after the apocalypse. Packed with humor, dark twists, and nonstop action.',
	 '/uploads/Fantasy/Fantasy11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0312','Bonds of Hercules',
	 'J.R.R. Tolkien’s timeless tale of Bilbo Baggins and his unexpected journey through Middle-earth. A beautifully illustrated modern classic of adventure and courage.',
	 '/uploads/Fantasy/Fantasy12.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0313','The Wrath of the Fallen',
	 'Paulo Coelho’s modern classic about following your dreams and listening to your heart. A parable of self-discovery that has inspired millions worldwide.',
	 '/uploads/Fantasy/Fantasy13.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0314','The Everlasting',
	 'A captivating romantic fantasy about Feyre, a huntress who becomes entangled in a dangerous faerie world after killing a wolf. Passion, magic, and peril intertwine in this spellbinding tale.',
	 '/uploads/Fantasy/Fantasy14.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0315','Hazelthorn',
	 'The beginning of Sarah J. Maas’s epic fantasy series. Celaena, a deadly assassin, is offered freedom if she can win a brutal competition to become the King’s Champion.',
	 '/uploads/Fantasy/Fantasy15.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0316','The Ascended',
	 'Nesta Archeron must face her past and her fiery bond with Cassian while the Night Court faces new threats. A passionate and empowering fantasy of love, trauma, and redemption.',
	 '/uploads/Fantasy/Fantasy16.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0317','The Things Gods Break',
	 'Aelin Galathynius returns to her empire seeking vengeance and freedom. The fourth book in the Throne of Glass series delivers powerful action and emotional depth.',
	 '/uploads/Fantasy/Fantasy1.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0318','Conform',
	 'A heartwarming companion to the ACOTAR series. Feyre and Rhys prepare for Winter Solstice as they heal from war and rebuild their court, balancing love and lingering scars.',
	 '/uploads/Fantasy/Fantasy18.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0319','King Sorrow',
	 'Feyre must play a deadly game of deception as war looms over Prythian. A breathtaking mix of love, betrayal, and sacrifice in the thrilling conclusion of the ACOTAR trilogy.',
	 '/uploads/Fantasy/Fantasy19.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0320','The Hearth Witch''s Guide to Magic & Murder',
	 'Aelin’s path to the throne becomes more perilous than ever as kingdoms fall into chaos. A gripping continuation of the Throne of Glass saga filled with war, magic, and destiny.',
	 '/uploads/Fantasy/Fantasy20.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),

-- LSP04 Business
	('SP0401','The Let Them Theory: A Life-Changing Tool That Millions of People Cannot Stop Talking About',
	 'A transformative guide by Mel Robbins that teaches how to stop controlling others, embrace acceptance, and live with more peace and freedom.',
	 '/uploads/Business/Business1.jpg',
	 'Mel Robbins','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0402','Atomic Habits: An Easy and Proven Way to Build Good Habits and Break Bad Ones',
	 'James Clear shares a powerful framework for creating better habits every day through small improvements that lead to extraordinary results.',
	 '/uploads/Business/Business2.jpg',
	 'James Clear','Avery', TRUE, 'LSP04', NULL),
	
	('SP0403','Never Split the Difference: Negotiating as If Your Life Depended on It',
	 'Former FBI negotiator Chris Voss reveals negotiation techniques that can be used in business and daily life to achieve win-win outcomes.',
	 '/uploads/Business/Business3.jpg',
	 'Chris Voss; Tahl Raz','Harper Business', TRUE, 'LSP04', NULL),
	
	('SP0404','Think and Grow Rich: The Landmark Bestseller Now Revised and Updated for the 21st Century',
	 'Napoleon Hill explains timeless principles for building wealth and success through mindset, persistence, and the power of thought.',
	 '/uploads/Business/Business4.jpg',
	 'Napoleon Hill','The Napoleon Hill Foundation', TRUE, 'LSP04', NULL),
	
	('SP0405','The 7 Habits of Highly Effective People: 30th Anniversary Edition',
	 'Stephen Covey presents seven timeless habits that help individuals grow personally and professionally to achieve lasting effectiveness.',
	 '/uploads/Business/Business5.jpg',
	 'Stephen R. Covey','Simon & Schuster', TRUE, 'LSP04', NULL),
	
	('SP0406','The Daily Stoic: 366 Meditations on Wisdom, Perseverance, and the Art of Living',
	 'Ryan Holiday offers daily reflections inspired by ancient Stoic philosophy to cultivate calm, discipline, and wisdom in modern life.',
	 '/uploads/Business/Business6.jpg',
	 'Ryan Holiday; Stephen Hanselman','Portfolio', TRUE, 'LSP04', NULL),
	
	('SP0407','Die with Zero: Getting All You Can from Your Money and Your Life',
	 'Bill Perkins encourages readers to spend money wisely to maximize life experiences instead of saving endlessly for an uncertain future.',
	 '/uploads/Business/Business7.jpg',
	 'Bill Perkins','Mariner Books', TRUE, 'LSP04', NULL),
	
	('SP0408','How to Invest 50-5000: The Small Investor''s Step-By-Step Plan for Low-Risk Investing in Today''s Economy',
	 'Nancy Dunnan provides simple strategies for beginners to start investing safely, build wealth, and make smart financial choices.',
	 '/uploads/Business/Business8.jpg',
	 'Nancy Dunnan','HarperCollins', TRUE, 'LSP04', NULL),
	
	('SP0409','Thinking, Fast and Slow',
	 'Nobel laureate Daniel Kahneman explores the two systems of human thinking and how biases influence decision making in work and life.',
	 '/uploads/Business/Business9.jpg',
	 'Daniel Kahneman','Farrar, Straus and Giroux', TRUE, 'LSP04', NULL),
	
	('SP0410','The 5 Types of Wealth: A Transformative Guide to Design Your Dream Life',
	 'James Dicks reveals five dimensions of true wealth beyond money, helping readers create balance, fulfillment, and lasting success.',
	 '/uploads/Business/Business10.jpg',
	 'James Dicks','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0411','The Unconventional Leader',
	 'A fresh playbook for leading beyond the rulebook—challenge assumptions, empower teams, and create accountable, adaptive cultures.',
	 '/uploads/Business/Business11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0412','Unconventional Business',
	 'Practical strategies for building bold, resilient companies—lean experiments, customer obsession, and differentiated positioning.',
	 '/uploads/Business/Business12.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0413','Integrity Moments',
	 'Short, thoughtful reflections on ethics at work—honesty, accountability, and trust as daily leadership practices.',
	 '/uploads/Business/Business13.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0414','10 Biblical Principles for Business',
	 'Faith-based guidance for entrepreneurship and leadership: stewardship, service, integrity, diligence, and wise decision-making.',
	 '/uploads/Business/Business14.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0415','Work that Matters',
	 'A purpose-driven approach to career design—clarify values, focus on impact, and align strengths to meaningful goals.',
	 '/uploads/Business/Business15.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0416','94X',
	 'A growth framework for scaling results—set clear metrics, systematize operations, and compound small wins into outsized outcomes.',
	 '/uploads/Business/Business16.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0417','Bet on Talent',
	 'How to build a standout culture by hiring for character, developing people, and keeping top performers engaged.',
	 '/uploads/Business/Business17.jpg',
	 'Dee Ann Turner','Baker Books', TRUE, 'LSP04', NULL),
	
	('SP0418','Every Good Endeavor',
	 'A perspective on integrating faith and work—vocation, calling, and contributing to the common good with excellence.',
	 '/uploads/Business/Business18.jpg',
	 'Timothy Keller; Katherine Leary Alsdorf','Dutton', TRUE, 'LSP04', NULL),
	
	('SP0419','Excellence Wins',
	 'Lessons in world-class service and leadership—set high standards, empower teams, and deliver consistency at scale.',
	 '/uploads/Business/Business19.jpg',
	 'Horst Schulze; Dean Merrill','Zondervan', TRUE, 'LSP04', NULL),
	
	('SP0420','FIRE in the Workplace',
	 'A practical guide to preventing and managing workplace crises—policies, training, and culture that reduce risk and burnout.',
	 '/uploads/Business/Business20.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),


-- LSP05 Drama
	('SP0501','Romeo and Juliet: No Fear Shakespeare Side-By-Side Plain English',
	 'A timeless tragic love story by William Shakespeare about two young lovers whose families are sworn enemies, leading to fate and heartbreak.',
	 '/uploads/Drama/Drama1.jpg',
	 'William Shakespeare; SparkNotes','SparkNotes', TRUE, 'LSP05', NULL),
	
	('SP0502','The Tragedy of Hamlet: Prince of Denmark',
	 'One of Shakespeares greatest plays that explores revenge, madness, and the struggle for moral integrity in a corrupt world.',
	 '/uploads/Drama/Drama2.jpg',
	 'William Shakespeare','Folger Shakespeare Library', TRUE, 'LSP05', NULL),
	
	('SP0503','The Big Lebowski',
	 'A darkly comic drama that follows Jeffrey \"The Dude\" Lebowski as he becomes entangled in a complex case of mistaken identity and absurd chaos.',
	 '/uploads/Drama/Drama3.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP05', NULL),
	
	('SP0504','Macbeth: No Fear Shakespeare Side-By-Side Plain English',
	 'The haunting story of ambition, guilt, and fate as Macbeth descends into madness after murdering the king to fulfill a prophecy.',
	 '/uploads/Drama/Drama4.jpg',
	 'William Shakespeare; SparkNotes','SparkNotes', TRUE, 'LSP05', NULL),
	
	('SP0505','Wait Until Dark',
	 'A gripping psychological thriller about a blind woman who must outsmart dangerous criminals searching for a hidden doll filled with drugs.',
	 '/uploads/Drama/Drama5.jpg',
	 'Frederick Knott','Dramatists Play Service', TRUE, 'LSP05', NULL),
	
	('SP0506','Harry Potter and the Cursed Child Parts One and Two: The Official Playscript of the Original West End Production',
	 'Set nineteen years after the original series, this stage play follows Harry Potters son Albus as he struggles with legacy, friendship, and destiny.',
	 '/uploads/Drama/Drama6.jpg',
	 'J.K. Rowling; Jack Thorne; John Tiffany','Little, Brown', TRUE, 'LSP05', NULL),
	
	('SP0507','Hamlet: No Fear Shakespeare Deluxe Student Edition',
	 'A student-friendly version of Shakespeares classic tragedy exploring the princes quest for truth, justice, and self-understanding.',
	 '/uploads/Drama/Drama7.jpg',
	 'William Shakespeare; SparkNotes','SparkNotes', TRUE, 'LSP05', NULL),
	
	('SP0508','Julius Caesar: No Fear Shakespeare Side-By-Side Plain English',
	 'This political drama portrays power, betrayal, and honor as Brutus joins a conspiracy to assassinate Julius Caesar for the good of Rome.',
	 '/uploads/Drama/Drama8.jpg',
	 'William Shakespeare; SparkNotes','SparkNotes', TRUE, 'LSP05', NULL),
	
	('SP0509','Fences',
	 'August Wilsons Pulitzer-winning play about an African American father in the 1950s struggling with his past, family, and unfulfilled dreams.',
	 '/uploads/Drama/Drama9.jpg',
	 'August Wilson','Plume', TRUE, 'LSP05', NULL),
	
	('SP0510','A Streetcar Named Desire',
	 'Tennessee Williamss iconic play exploring fragility, illusion, and emotional decay through the life of Blanche DuBois in New Orleans.',
	 '/uploads/Drama/Drama10.jpg',
	 'Tennessee Williams','New Directions', TRUE, 'LSP05', NULL),
	
	('SP0511','Unspoken: A Riveting Novel of Suspense',
	 'A timeless tragic love story by William Shakespeare about two young lovers whose families are sworn enemies, leading to fate and heartbreak.',
	 '/uploads/Drama/Drama11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP05', NULL),
	
	('SP0512','The Threads Remain',
	 'One of Shakespeares greatest plays that explores revenge, madness, and the struggle for moral integrity in a corrupt world.',
	 '/uploads/Drama/Drama12.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP05', NULL),
	
	('SP0513','Teen Girls Handbook: From Making Friends, Avoiding Drama, Overcoming Insecurities, Planning for the Future, and Everything Else Along the Way to Growing Up',
	 'A darkly comic drama that follows Jeffrey The Dude Lebowski as he becomes entangled in a complex case of mistaken identity and absurd chaos.',
	 '/uploads/Drama/Drama13.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP05', NULL),
	
	('SP0514','Living Trusts, Wills & Estate Planning for Seniors - The Complete 3-in-1 Guide: Protect Your Assets, Avoid Probate & Create an Estate Plan Without Costly Lawyers or Family Drama (+Will & Trust Forms)',
	 'The haunting story of ambition, guilt, and fate as Macbeth descends into madness after murdering the king to fulfill a prophecy.',
	 '/uploads/Drama/Drama14.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP05', NULL),
	
	('SP0515','No-Drama Discipline: The Whole-Brain Way to Calm the Chaos and Nurture Your Childs Developing Mind',
	 'A gripping psychological thriller about a blind woman who must outsmart dangerous criminals searching for a hidden doll filled with drugs.',
	 '/uploads/Drama/Drama15.jpg',
	 'Daniel J. Siegel; Tina Payne Bryson','Bantam', TRUE, 'LSP05', NULL),
	
	('SP0516','Drama: A Graphic Novel',
	 'Set nineteen years after the original series, this stage play follows Harry Potters son Albus as he struggles with legacy, friendship, and destiny.',
	 '/uploads/Drama/Drama16.jpg',
	 'Raina Telgemeier','Scholastic Graphix', TRUE, 'LSP05', NULL),
	
	('SP0517','Society of Lies: Reeses Book Club: A Novel',
	 'A student-friendly version of Shakespeares classic tragedy exploring the princes quest for truth, justice, and self-understanding.',
	 '/uploads/Drama/Drama17.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP05', NULL),
	
	('SP0518','Llama Llama Holiday Drama',
	 'This political drama portrays power, betrayal, and honor as Brutus joins a conspiracy to assassinate Julius Caesar for the good of Rome.',
	 '/uploads/Drama/Drama18.jpg',
	 'Anna Dewdney','Viking Books for Young Readers', TRUE, 'LSP05', NULL),
	
	('SP0519','A Hollywood Ending: The Dreams and Drama of the LeBron Lakers',
	 'August Wilsons Pulitzer-winning play about an African American father in the 1950s struggling with his past, family, and unfulfilled dreams.',
	 '/uploads/Drama/Drama19.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP05', NULL),
	
	('SP0520','Smart Girls Guide: Drama, Rumors & Secrets: Staying True to Yourself in Changing Times',
	 'Tennessee Williamss iconic play exploring fragility, illusion, and emotional decay through the life of Blanche DuBois in New Orleans.',
	 '/uploads/Drama/Drama20.jpg',
	 'Nancy Holyoke','American Girl Publishing', TRUE, 'LSP05', NULL),

-- LSP06 Biography
	('SP0601','Mans Search for Meaning',
	 'A powerful memoir by Viktor E. Frankl, a Holocaust survivor and psychiatrist, exploring how finding purpose gives life meaning even in the darkest times.',
	 '/uploads/Biography/Biography1.jpg',
	 'Viktor E. Frankl','Beacon Press', TRUE, 'LSP06', NULL),
	
	('SP0602','Night',
	 'Elie Wiesel recounts his harrowing experiences as a teenager in Nazi concentration camps during the Holocaust. A haunting reflection on survival, faith, and humanity.',
	 '/uploads/Biography/Biography2.jpg',
	 'Elie Wiesel','Hill and Wang', TRUE, 'LSP06', NULL),
	
	('SP0603','When Breath Becomes Air',
	 'Written by neurosurgeon Paul Kalanithi as he faced terminal cancer, this moving memoir reflects on life, death, and what makes life worth living.',
	 '/uploads/Biography/Biography3.jpg',
	 'Paul Kalanithi','Random House', TRUE, 'LSP06', NULL),
	
	('SP0604','To Rescue the Constitution: George Washington and the Fragile American Experiment',
	 'Historian Bret Baier explores how George Washington helped save the newly formed United States from collapse, protecting the fragile dream of democracy.',
	 '/uploads/Biography/Biography4.jpg',
	 'Bret Baier; Catherine Whitney','Various Publishers', TRUE, 'LSP06', NULL),
	
	('SP0605','Hillbilly Elegy: A Memoir of a Family and Culture in Crisis',
	 'J. D. Vance offers a deeply personal memoir about growing up in a Rust Belt town, capturing the struggles and values of America''s white working class.',
	 '/uploads/Biography/Biography5.jpg',
	 'J. D. Vance','Harper', TRUE, 'LSP06', NULL),
	
	('SP0606','Raising Hare: A Memoir',
	 'A heartfelt memoir exploring motherhood, resilience, and personal growth while raising a child and rediscovering one''s own identity.',
	 '/uploads/Biography/Biography6.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP06', NULL),
	
	('SP0607','The Hiding Place',
	 'Corrie ten Boom tells the true story of her family''s efforts to hide Jews during World War II and her unwavering faith amid imprisonment and loss.',
	 '/uploads/Biography/Biography7.jpg',
	 'Corrie ten Boom; John Sherrill; Elizabeth Sherrill','Chosen Books', TRUE, 'LSP06', NULL),
	
	('SP0608','Trump: The Art of the Deal',
	 'Donald J. Trump and Tony Schwartz share Trump''s early life, business philosophy, and deals that shaped his rise as a real estate magnate.',
	 '/uploads/Biography/Biography8.jpg',
	 'Donald J. Trump; Tony Schwartz','Random House', TRUE, 'LSP06', NULL),
	
	('SP0609','Assata: An Autobiography',
	 'Assata Shakur recounts her life as a civil rights activist and member of the Black Liberation Army, chronicling her fight for justice and her controversial escape to Cuba.',
	 '/uploads/Biography/Biography9.jpg',
	 'Assata Shakur','Lawrence Hill Books', TRUE, 'LSP06', NULL),
	
	('SP0610','Endurance: Shackleton''s Incredible Voyage',
	 'Alfred Lansing presents an inspiring account of Ernest Shackleton''s legendary Antarctic expedition, a story of courage, leadership, and survival.',
	 '/uploads/Biography/Biography10.jpg',
	 'Alfred Lansing','Basic Books', TRUE, 'LSP06', NULL),
	
	('SP0611','Finding My Way',
	 'A powerful reflection on identity, resilience, and purpose across seasons of change.',
	 '/uploads/Biography/Biography11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP06', NULL),
	
	('SP0612','Vagabond: A Memoir',
	 'A candid memoir about reinvention, solitude, and the grit required to live on one''s own terms.',
	 '/uploads/Biography/Biography12.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP06', NULL),
	
	('SP0613','Paper Girl: A Memoir of Home and Family in a Fractured America',
	 'A moving account of growing up amid cultural divides, exploring belonging, family, and home.',
	 '/uploads/Biography/Biography13.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP06', NULL),
	
	('SP0614','Joyride: A Memoir',
	 'A high-spirited chronicle of risk, reinvention, and finding joy on the open road.',
	 '/uploads/Biography/Biography14.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP06', NULL),
	
	('SP0615','The Girl Bandits of the Warsaw Ghetto: The True Story of Five Courageous Young Women Who Sparked an Uprising',
	 'The untold stories of five young women whose bravery fueled resistance in the Warsaw Ghetto.',
	 '/uploads/Biography/Biography15.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP06', NULL),
	
	('SP0616','Motherland: A Feminist History of Modern Russia, from Revolution to Autocracy',
	 'A sweeping history of modern Russia through the lens of women''s lives, power, and resistance.',
	 '/uploads/Biography/Biography16.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP06', NULL),
	
	('SP0617','Lincoln''s Ghost: Houdini''s War on Spiritualism and the Dark Conspiracy Against the American Presidency',
	 'An investigation into spiritualism, stagecraft, and politics in the shadow of Lincoln''s legacy.',
	 '/uploads/Biography/Biography17.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP06', NULL),
	
	('SP0618','Unbearable: Five Women and the Perils of Pregnancy in America',
	 'Five intimate narratives revealing systemic challenges of pregnancy and maternal care in America.',
	 '/uploads/Biography/Biography18.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP06', NULL),
	
	('SP0619','The Nine Lives of Christopher Columbus',
	 'A provocative reexamination of Columbus through multiple historical lenses and myths.',
	 '/uploads/Biography/Biography19.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP06', NULL),
	
	('SP0620','We Did Ok, Kid: A Memoir',
	 'A tender, humorous memoir about family, perseverance, and finding pride in imperfect journeys.',
	 '/uploads/Biography/Biography20.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP06', NULL),




-- LSP07 Cook
	('SP0701','The Super Easy Teen Cookbook: 75 Simple Step-By-Step Recipes',
	 'A fun and easy cookbook for teens with 75 simple, foolproof recipes. Perfect for beginners who want to learn how to cook quick, tasty, and healthy meals at home.',
	 '/uploads/Cook/Cook1.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0702','The Super Easy Teen Cookbook: 75 Simple Step-By-Step Recipes',
	 'Step-by-step recipes designed for young cooks. Learn to make everyday dishes, snacks, and desserts with clear instructions and helpful cooking tips.',
	 '/uploads/Cook/Cook2.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0703','The Complete Cookbook for Young Chefs: 100+ Recipes that You will Love to Cook and Eat',
	 'Created by Americas Test Kitchen, this award-winning cookbook helps kids master over 100 delicious recipes—from breakfast to dinner—with full-color photos and step-by-step guides.',
	 '/uploads/Cook/Cook3.jpg',
	 'America''s Test Kitchen Kids','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0704','Half Baked Harvest Quick & Cozy: A Cookbook',
	 'Tieghan Gerard shares comfort food recipes that are quick, cozy, and full of flavor. Includes creative twists on classic dishes and stunning food photography.',
	 '/uploads/Cook/Cook4.jpg',
	 'Tieghan Gerard','Clarkson Potter', TRUE, 'LSP07', NULL),
	
	('SP0705','The Complete Baking Book for Young Chefs: 100+ Sweet and Savory Recipes That You will Love to Bake, Share and Eat!',
	 'A baking bible for kids and teens with over 100 tested recipes for cookies, cakes, breads, and savory treats. Learn baking basics and have fun creating from scratch.',
	 '/uploads/Cook/Cook5.jpg',
	 'America''s Test Kitchen Kids','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0706','Salt, Fat, Acid, Heat: Mastering the Elements of Good Cooking',
	 'Samin Nosrat explains the four key elements of cooking—salt, fat, acid, and heat—through science, storytelling, and recipes that make you a confident home cook.',
	 '/uploads/Cook/Cook6.jpg',
	 'Samin Nosrat','Simon & Schuster', TRUE, 'LSP07', NULL),
	
	('SP0707','The Complete Anti-Inflammatory Diet for Beginners: A No-Stress Meal Plan with Easy Recipes to Heal the Immune System',
	 'A practical beginner’s guide featuring meal plans and simple recipes designed to reduce inflammation and boost overall health naturally.',
	 '/uploads/Cook/Cook7.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0708','Half Baked Harvest Super Simple: More Than 125 Recipes for Instant, Overnight, Meal-Prepped, and Easy Comfort Foods: A Cookbook',
	 '125 effortless comfort food recipes for busy people. Includes make-ahead, slow cooker, and 30-minute dishes that taste homemade and hearty.',
	 '/uploads/Cook/Cook8.jpg',
	 'Tieghan Gerard','Clarkson Potter', TRUE, 'LSP07', NULL),
	
	('SP0709','The King Arthur Baking Company Big Book of Bread: 125+ Recipes for Every Baker (a Cookbook)',
	 'A complete guide to bread baking with over 125 tried-and-true recipes—from rustic loaves to soft sandwich breads—by America’s most trusted baking experts.',
	 '/uploads/Cook/Cook9.jpg',
	 'King Arthur Baking Company','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0710','The Anti-Inflammatory Diet Slow Cooker Cookbook: Prep-And-Go Recipes for Long-Term Healing',
	 'A collection of slow cooker recipes that promote healing and reduce inflammation. Features nutritious meals that are easy to prepare and full of flavor.',
	 '/uploads/Cook/Cook10.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0711','Beer: History, Legends, Trends',
	 'A fun and easy cookbook for teens with 75 simple, foolproof recipes. Perfect for beginners who want to learn how to cook quick, tasty, and healthy meals at home.',
	 '/uploads/Cook/Cook11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0712','Mini Vietnamese Favorites',
	 'Step-by-step recipes designed for young cooks. Learn to make everyday dishes, snacks, and desserts with clear instructions and helpful cooking tips.',
	 '/uploads/Cook/Cook12.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0713','Mini Quick & Easy Vietnamese Cooking',
	 'Created by Americas Test Kitchen, this award-winning cookbook helps kids master over 100 delicious recipes—from breakfast to dinner—with full-color photos and step-by-step guides.',
	 '/uploads/Cook/Cook13.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0714','Mini Homestyle Vietnamese Cooking',
	 'Tieghan Gerard shares comfort food recipes that are quick, cozy, and full of flavor. Includes creative twists on classic dishes and stunning food photography.',
	 '/uploads/Cook/Cook14.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0715','The Bar(c)art Book: The Art of Crafting Tasty and Timeless Cocktails',
	 'A baking bible for kids and teens with over 100 tested recipes for cookies, cakes, breads, and savory treats. Learn baking basics and have fun creating from scratch.',
	 '/uploads/Cook/Cook15.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0716','Peñin Guide Spanish Wine 2025 (Spanish Wines)',
	 'Samin Nosrat explains the four key elements of cooking—salt, fat, acid, and heat—through science, storytelling, and recipes that make you a confident home cook.',
	 '/uploads/Cook/Cook16.jpg',
	 'Peñín Guide','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0717','Dac Biet: An Extra-Special Vietnamese Cookbook',
	 'A practical beginner’s guide featuring meal plans and simple recipes designed to reduce inflammation and boost overall health naturally.',
	 '/uploads/Cook/Cook17.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0718','Quick and Easy Vietnamese: Everyday Vietnamese Recipes for the Home Cook',
	 '125 effortless comfort food recipes for busy people. Includes make-ahead, slow cooker, and 30-minute dishes that taste homemade and hearty.',
	 '/uploads/Cook/Cook18.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0719','Italian Coastal: Recipes and Stories from Where the Land Meets the Sea',
	 'A complete guide to bread baking with over 125 tried-and-true recipes—from rustic loaves to soft sandwich breads—by America’s most trusted baking experts.',
	 '/uploads/Cook/Cook19.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0720','Recipes from my Vietnamese Kitchen',
	 'A collection of slow cooker recipes that promote healing and reduce inflammation. Features nutritious meals that are easy to prepare and full of flavor.',
	 '/uploads/Cook/Cook20.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),



-- LSP08 Poetry
	('SP0801','Beowulf: A New Verse Translation',
	 'A timeless epic of heroism and fate. Seamus Heaney’s award-winning translation brings new life to this ancient tale of courage and mortality.',
	 '/uploads/Poetry/Poetry1.jpg',
	 'Seamus Heaney (Translator)','W. W. Norton & Company', TRUE, 'LSP08', NULL),
	
	('SP0802','Where the Sidewalk Ends: Poems and Drawings',
	 'A beloved collection of humorous and heartfelt poems paired with whimsical drawings from the creator of The Giving Tree.',
	 '/uploads/Poetry/Poetry2.jpg',
	 'Shel Silverstein','HarperCollins', TRUE, 'LSP08', NULL),
	
	('SP0803','Save Me An Orange',
	 'A tender debut exploring pain, love, and self-acceptance. Hayley’s poetry captures fleeting joy and the beauty of simply sharing an orange.',
	 '/uploads/Poetry/Poetry3.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),
	
	('SP0804','Lord of the Butterflies',
	 'Andrea Gibson’s award-winning poetry on gender, love, and loss — powerful, emotional, and crafted with lyrical precision.',
	 '/uploads/Poetry/Poetry4.jpg',
	 'Andrea Gibson','Button Poetry', TRUE, 'LSP08', NULL),
	
	('SP0805','The Tears That Taught Me',
	 'A moving journey through heartbreak, healing, and rebirth. Each poem invites readers to embrace emotion and bloom with purpose.',
	 '/uploads/Poetry/Poetry5.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),
	
	('SP0806','Devotions: The Selected Poems of Mary Oliver',
	 'A landmark collection spanning five decades of Mary Oliver’s Pulitzer-winning poetry — celebrating nature, love, and the human spirit.',
	 '/uploads/Poetry/Poetry6.jpg',
	 'Mary Oliver','Penguin Press', TRUE, 'LSP08', NULL),
	
	('SP0807','The Prophet (Reader''s Library Classics)',
	 'Kahlil Gibran’s spiritual masterpiece exploring love, life, and purpose. A timeless collection of poetic wisdom and soulful reflection.',
	 '/uploads/Poetry/Poetry7.jpg',
	 'Kahlil Gibran','Various Publishers', TRUE, 'LSP08', NULL),
	
	('SP0808','A Light in the Attic (Special Edition)',
	 'Shel Silverstein’s classic poetry collection returns with new poems — full of wit, wonder, and his signature illustrations.',
	 '/uploads/Poetry/Poetry8.jpg',
	 'Shel Silverstein','HarperCollins', TRUE, 'LSP08', NULL),
	
	('SP0809','Heart Talk: Poetic Wisdom for a Better Life',
	 'Cleo Wade blends poetry and positivity in this uplifting collection of daily affirmations and modern wisdom for the heart and soul.',
	 '/uploads/Poetry/Poetry9.jpg',
	 'Cleo Wade','Atria Books', TRUE, 'LSP08', NULL),
	
	('SP0810','I Hope You Remember: Poems on Loving, Longing, and Living',
	 'Josie Balka’s debut poetry collection on love, nostalgia, and growth — honest, relatable, and deeply emotional.',
	 '/uploads/Poetry/Poetry10.jpg',
	 'Josie Balka','Various Publishers', TRUE, 'LSP08', NULL),


	

-- LSP09 Art
	('SP0901','The Art Thief: A True Story of Love, Crime, and a Dangerous Obsession',
	 'The true story of Stéphane Breitwieser — the world’s most successful art thief. For years he stole masterpieces from museums across Europe, keeping them not for profit, but for love.',
	 '/uploads/Art/Art1.jpg',
	 'Michael Finkel','Knopf', TRUE, 'LSP09', NULL),
	
	('SP0902','The Reverse Coloring Book: The Book Has the Colors, You Draw the Lines!',
	 'A creative twist on coloring — the colors are already there, and you draw the lines. Let your imagination flow freely across watercolor pages filled with shapes and inspiration.',
	 '/uploads/Art/Art2.jpg',
	 'Kendra Norton','Workman Publishing', TRUE, 'LSP09', NULL),
	
	('SP0903','How To Draw Everything: 600 Simple Step By Step Drawings For Kids Ages 4 to 8',
	 'An easy, fun drawing book with over 600 guided images for kids. Boosts creativity, pen control, and artistic confidence with themes from animals to holidays.',
	 '/uploads/Art/Art3.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP09', NULL),
	
	('SP0904','The Art of War',
	 'Sun Tzu’s timeless guide to strategy, leadership, and wisdom. Lessons from ancient warfare that still shape modern business, politics, and personal success.',
	 '/uploads/Art/Art4.jpg',
	 'Sun Tzu','Various Publishers', TRUE, 'LSP09', NULL),
	
	('SP0905','Perspective Made Easy',
	 'A beginner-friendly guide to mastering perspective in art. With 250+ illustrations, this classic helps you draw depth, distance, and dimension with confidence.',
	 '/uploads/Art/Art5.jpg',
	 'Ernest R. Norling','Dover Publications', TRUE, 'LSP09', NULL),
	
	('SP0906','All the Beauty in the World: The Metropolitan Museum of Art and Me',
	 'A museum guard’s moving memoir inside the halls of the Met. A deeply personal journey through art, humanity, and the quiet power of beauty.',
	 '/uploads/Art/Art6.jpg',
	 'Patrick Bringley','Avid Reader Press', TRUE, 'LSP09', NULL),
	
	('SP0907','The Complete Illustrated Kama Sutra',
	 'An artistic and cultural celebration of the ancient Kama Sutra, featuring 260 exquisite artworks from museums worldwide. A timeless tribute to beauty and connection.',
	 '/uploads/Art/Art7.jpg',
	 'Lance Dane','Inner Traditions', TRUE, 'LSP09', NULL),
	
	('SP0908','Morpho: Simplified Forms – Anatomy for Artists',
	 'Michel Lauricella simplifies human anatomy into basic shapes, helping artists understand structure and proportion. A must-have for improving figure drawing skills.',
	 '/uploads/Art/Art8.jpg',
	 'Michel Lauricella','Rocky Nook', TRUE, 'LSP09', NULL),
	
	('SP0909','Paint by Sticker: Works of Art',
	 'Recreate twelve famous paintings sticker by sticker — from Van Gogh to Renoir. Relaxing, creative, and frame-worthy when finished.',
	 '/uploads/Art/Art9.jpg',
	 'Workman Publishing','Workman Publishing', TRUE, 'LSP09', NULL),
	
	('SP0910','The Artist’s Way Workbook',
	 'A practical workbook for unlocking creativity and self-expression. Filled with exercises and reflections to help you rediscover your artistic potential.',
	 '/uploads/Art/Art10.jpg',
	 'Julia Cameron','TarcherPerigee', TRUE, 'LSP09', NULL),
	
	('SP0911','Pattern Cutting Deconstructed: Wearable Art',
	 'An exploration of experimental pattern cutting and sculptural garments—step-by-step methods, draping ideas, and case studies for wearable art.',
	 '/uploads/Art/Art11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP09', NULL),
	
	('SP0912','Fashion in the Metaverse: Designs and Technologies Shaping the Future',
	 'A forward-looking guide to digital fashion, virtual runways, NFTs, avatars, and the tools redefining design in immersive worlds.',
	 '/uploads/Art/Art12.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP09', NULL),
	
	('SP0913','Aino + Alvar Aalto: A Life Together',
	 'An intimate portrait of Aino and Alvar Aalto’s partnership, featuring modernist architecture, furniture, and correspondence across their careers.',
	 '/uploads/Art/Art13.jpg',
	 'Various Authors','Phaidon', TRUE, 'LSP09', NULL),
	
	('SP0914','S.O.A.P class: Create your own set of prompt cards',
	 'A hands-on workbook to design personal creative prompt cards—frameworks, templates, and exercises to spark ideas on demand.',
	 '/uploads/Art/Art14.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP09', NULL),
	
	('SP0915','Sybil and David Yurman: Artists and Jewelers',
	 'A visual history of the Yurmans’ studio practice—sculptural jewelry, brand evolution, and signature cable motifs in rich photography.',
	 '/uploads/Art/Art16.jpg',
	 'Various Authors','Rizzoli', TRUE, 'LSP09', NULL),
	
	('SP0916','Japan Style',
	 'A curated survey of Japanese aesthetics—from wabi-sabi interiors to craft, textiles, and garden design—blending tradition with minimalism.',
	 '/uploads/Art/Art16.jpg',
	 'Various Authors','Tuttle Publishing', TRUE, 'LSP09', NULL),
	
	('SP0917','Paul Smith',
	 'A vibrant monograph on designer Paul Smith: playful tailoring, bold color and stripe motifs, studio snapshots, and behind-the-scenes stories.',
	 '/uploads/Art/Art17.jpg',
	 'Paul Smith','Phaidon', TRUE, 'LSP09', NULL),


	
-- LSP10 Architecture
	('SP1001','Get Your House Right: Architectural Elements to Use & Avoid',
	 'A comprehensive guide explaining why many traditional-style buildings go wrong, how architectural elements work together, and how to avoid design mistakes throughout the building process.',
	 '/uploads/Architecture/Architecture1.jpg',
	 'Marianne Cusato; Ben Pentreath; Richard Sammons','Sterling', TRUE, 'LSP10', NULL),
	
	('SP1002','Feng Shui Modern',
	 'A modern guide to applying ancient feng shui principles to any living space. Interior designer Cliff Tan explains how to arrange rooms, choose colors, and use space to create harmony and balance in your home.',
	 '/uploads/Architecture/Architecture2.jpg',
	 'Cliff Tan','Various Publishers', TRUE, 'LSP10', NULL),
	
	('SP1003','Goodbye, Things: The New Japanese Minimalism',
	 'A bestselling Japanese guide to minimalist living that shows how owning less can lead to greater happiness and freedom.',
	 '/uploads/Architecture/Architecture3.jpg',
	 'Fumio Sasaki','W. W. Norton & Company', TRUE, 'LSP10', NULL),
	
	('SP1004','The Death and Life of Great American Cities',
	 'Jane Jacobs’s classic critique of modern urban planning. She argues that true city vitality comes from diversity, street life, and human scale rather than rigid design and planning theories.',
	 '/uploads/Architecture/Architecture4.jpg',
	 'Jane Jacobs','Vintage', TRUE, 'LSP10', NULL),
	
	('SP1005','Surf Shack: Laid-Back Living by the Water',
	 'A beautiful look inside cozy surfside homes around the world. Showcases relaxed interiors that reflect the easygoing lifestyle of surfers and beach lovers.',
	 '/uploads/Architecture/Architecture5.jpg',
	 'Nina Freudenberger; Heather Summerville; Brittany Ambridge','Clarkson Potter', TRUE, 'LSP10', NULL),
	
	('SP1006','Curate: Inspiration for an Individual Home',
	 'A beautifully photographed guide showing how to create a home that reflects your personality. Covers elements like color, light, textiles, and art to help you design your own unique space.',
	 '/uploads/Architecture/Architecture6.jpg',
	 'Lynda Gardener; Ali Heath; Marnie Hawson','Hardie Grant', TRUE, 'LSP10', NULL),
	
	('SP1007','Architectural Digest at 100: A Century of Style',
	 'A stunning collection celebrating 100 years of Architectural Digest, featuring the homes of global icons and the work of world-renowned architects and designers.',
	 '/uploads/Architecture/Architecture7.jpg',
	 'Architectural Digest Editors','Abrams', TRUE, 'LSP10', NULL),
	
	('SP1008','The Interior Design Handbook: Furnish, Decorate, and Style Your Space',
	 'A practical handbook that breaks down interior design principles—covering color, balance, lighting, and proportion—to help you create stylish and functional spaces.',
	 '/uploads/Architecture/Architecture8.jpg',
	 'Frida Ramstedt','Clarkson Potter', TRUE, 'LSP10', NULL),
	
	('SP1009','Resident Dog (Volume Two): Incredible Homes and the Dogs Who Live There',
	 'A delightful book showcasing breathtaking architecture and the dogs who call these homes their own. Combines stunning photography with warmth and humor.',
	 '/uploads/Architecture/Architecture9.jpg',
	 'Nicole England','Thames & Hudson', TRUE, 'LSP10', NULL),
	
	('SP1010','Live Beautiful',
	 'A design book by Athena Calderone featuring elegant homes and expert tips on creating interiors that are both personal and beautiful, blending texture, color, and story.',
	 '/uploads/Architecture/Architecture10.jpg',
	 'Athena Calderone','Abrams', TRUE, 'LSP10', NULL),
	
	('SP1011','Sustainable and Regenerative Materials for Architecture: A Sourcebook',
	 'A comprehensive guide explaining why many traditional-style buildings go wrong, how architectural elements work together, and how to avoid design mistakes throughout the building process.',
	 '/uploads/Architecture/Architecture11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP10', NULL),
	
	('SP1012','Aino + Alvar Aalto: A Life Together',
	 'A modern guide to applying ancient feng shui principles to any living space. Interior designer Cliff Tan explains how to arrange rooms, choose colors, and use space to create harmony and balance in your home.',
	 '/uploads/Architecture/Architecture12.jpg',
	 'Various Authors','Phaidon', TRUE, 'LSP10', NULL),
	
	('SP1013','Paul Cocksedge: Reflections',
	 'A bestselling Japanese guide to minimalist living that shows how owning less can lead to greater happiness and freedom.',
	 '/uploads/Architecture/Architecture13.jpg',
	 'Paul Cocksedge','Rizzoli', TRUE, 'LSP10', NULL),
	
	('SP1014','Alessandro Mendini: Imagination Takes Command',
	 'Jane Jacobs’s classic critique of modern urban planning. She argues that true city vitality comes from diversity, street life, and human scale rather than rigid design and planning theories.',
	 '/uploads/Architecture/Architecture14.jpg',
	 'Various Authors','Rizzoli', TRUE, 'LSP10', NULL),
	
	('SP1015','Mies',
	 'A beautiful look inside cozy surfside homes around the world. Showcases relaxed interiors that reflect the easygoing lifestyle of surfers and beach lovers.',
	 '/uploads/Architecture/Architecture15.jpg',
	 'Detlef Mertins','Phaidon', TRUE, 'LSP10', NULL),
	
	('SP1016','Atlas of Mid-Century Modern Masterpieces',
	 'A beautifully photographed guide showing how to create a home that reflects your personality. Covers elements like color, light, textiles, and art to help you design your own unique space.',
	 '/uploads/Architecture/Architecture16.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP10', NULL),
	
	('SP1017','Elevated: Art on the High Line',
	 'A stunning collection celebrating 100 years of Architectural Digest, featuring the homes of global icons and the work of world-renowned architects and designers.',
	 '/uploads/Architecture/Architecture17.jpg',
	 'Various Authors','Phaidon', TRUE, 'LSP10', NULL),
	
	('SP1018','Thomas Heatherwick: Making',
	 'A practical handbook that breaks down interior design principles—covering color, balance, lighting, and proportion—to help you create stylish and functional spaces.',
	 '/uploads/Architecture/Architecture18.jpg',
	 'Thomas Heatherwick','Thames & Hudson', TRUE, 'LSP10', NULL),
	
	('SP1019','Sponge Park: Gowanus Canal',
	 'A delightful book showcasing breathtaking architecture and the dogs who call these homes their own. Combines stunning photography with warmth and humor.',
	 '/uploads/Architecture/Architecture19.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP10', NULL),
	
	('SP1020','Peter Zumthor: Buildings and Projects 1985-2013',
	 'A design book by Athena Calderone featuring elegant homes and expert tips on creating interiors that are both personal and beautiful, blending texture, color, and story.',
	 '/uploads/Architecture/Architecture20.jpg',
	 'Peter Zumthor','Scheidegger & Spiess', TRUE, 'LSP10', NULL),

-- LSP11 Modelkit
	('SP1101','Assembly Model Bandai MG 1/100 ZZ GUNDAM Ver.Ka',
	 'Katoki "Ver.Ka" redesign of ZZ Gundam with full transformation, solid locking joints, sharp color separation, and extensive water-slide decals.',
	 '/uploads/Modelkit/Modelkit1.jpg',
	 'Bandai','BANDAI SPIRITS', TRUE, 'LSP11', NULL),
	
	('SP1102','MNP-XH02 Cao Ren - Motor Nuclear',
	 'Highly detailed Motor Nuclear kit of Cao Ren featuring elaborate armor, large halberd loadout, and wide articulation for dynamic combat poses.',
	 '/uploads/Modelkit/Modelkit2.jpg',
	 'Motor Nuclear','Motor Nuclear', TRUE, 'LSP11', NULL),
	
	('SP1103','Bandai MG 1/100 Turn A GUNDAM (∀ Gundam) Assembly Model',
	 'MG Turn A Gundam with signature chest "mustache", snap-fit build, core fighter gimmick, and accessories like beam rifle, shield, and sabers.',
	 '/uploads/Modelkit/Modelkit3.jpg',
	 'Bandai','BANDAI SPIRITS', TRUE, 'LSP11', NULL),
	
	('SP1104','Bandai MG 1/100 Destiny Gundam Assembled Model',
	 'Iconic Destiny Gundam with high-mobility frame and rich equipment: anti-ship sword, long-range cannon, beam boomerangs, shield, and effect parts compatibility.',
	 '/uploads/Modelkit/Modelkit4.jpg',
	 'Bandai','BANDAI SPIRITS', TRUE, 'LSP11', NULL),
	
	('SP1105','Motor Nuclear MNP-XH03 AOBING (Model Kit)',
	 'Dragon-themed Ao Bing mecha with layered armor, optional clear parts, large spear, and premium decals for a striking display.',
	 '/uploads/Modelkit/Modelkit5.jpg',
	 'Motor Nuclear','Motor Nuclear', TRUE, 'LSP11', NULL),
	
	('SP1106','Metal Frame Pre-Assembled Model MNQ-XH09X Guan Yu (with Horse) - Motor Nuclear',
	 'Pre-assembled metal inner frame set of Guan Yu with horse. Features die-cast heft, Green Dragon Crescent Blade, and stable mounted poses.',
	 '/uploads/Modelkit/Modelkit6.jpg',
	 'Motor Nuclear','Motor Nuclear', TRUE, 'LSP11', NULL),
	
	('SP1107','Motor Nuclear MNP-XH04 NEZHA (Natra) Model Kit',
	 'Agile Nezha-inspired mecha with ring/rope-style weapon motifs, expressive articulation, and booster options for airborne action shots.',
	 '/uploads/Modelkit/Modelkit7.jpg',
	 'Motor Nuclear','Motor Nuclear', TRUE, 'LSP11', NULL),
	
	('SP1108','Pre-Assembled Metal Build CD-03 Vermilion Bird - Cangdao Model',
	 'Metal-build style Vermilion Bird with avian-themed armor, sturdy die-cast content, precise paintwork, and a display base for dramatic poses.',
	 '/uploads/Modelkit/Modelkit8.jpg',
	 'Cangdao Model','Cangdao Model', TRUE, 'LSP11', NULL),


-- LSP12 Figure
	('SP1201','Genuine Bandai Dragon Ball – Yamcha & Puar History Box Figure',
	 'Yamcha cùng Puar trong dòng History Box, tạo dáng động và chi tiết sắc nét; sơn đổ bóng đẹp và độ hoàn thiện cao cho fan Dragon Ball.',
	 '/uploads/Figure/Figure1.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1202','Dragon Ball – Gogeta Super Saiyan Grandista Figure genuine Bandai',
	 'Gogeta dạng Super Saiyan thuộc dòng Grandista với tỉ lệ lớn, cơ bắp khắc họa rõ ràng và nếp áo mượt, thích hợp trưng bày nổi bật.',
	 '/uploads/Figure/Figure2.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1203','Dragon Ball – Majinbuu Ichiban Kuji Last One Figure (Fat Buu) genuine Bandai',
	 'Phiên bản Last One của Majin Buu (Fat Buu) trong Ichiban Kuji: kích thước ấn tượng, khuôn mặt biểu cảm và lớp sơn bóng bẩy.',
	 '/uploads/Figure/Figure3.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1204','Genuine Bandai Yatogami Tohka Glitter & Glamours – Date A Live V Figure',
	 'Tohka Yatogami từ Date A Live trong dòng Glitter & Glamours: tạo dáng duyên dáng, nếp váy chi tiết và sắc tím đặc trưng.',
	 '/uploads/Figure/Figure4.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1205','Dragon Ball – Vegeta Blue Blood Of Saiyans Figure genuine Bandai',
	 'Vegeta dạng Super Saiyan Blue thuộc series Blood of Saiyans, tư thế mạnh mẽ; hiệu ứng shading và vết xước tăng độ chân thực.',
	 '/uploads/Figure/Figure5.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1206','Dragon Ball – Songoku Super Saiyan 3 1/6 Scale Resin Model DK Studio',
	 'Mô hình resin tỉ lệ 1/6 của Son Goku Super Saiyan 3 từ DK Studio: mái tóc dài hoành tráng, dáng đứng vững và chi tiết cơ bắp nổi khối.',
	 '/uploads/Figure/Figure6.jpg',
	 'DK Studio','DK Studio', TRUE, 'LSP12', NULL),
	
	('SP1207','One Piece – Doflamingo Ichiban Kuji Masterlise Figure genuine Bandai',
	 'Doflamingo phiên bản Masterlise với áo lông biểu tượng, tạo dáng tự tin; chi tiết gân cơ và đường gấp vải được xử lý kỹ.',
	 '/uploads/Figure/Figure7.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1208','One Piece – Zoro Dressrosa Battle Ichiban Kuji Figure genuine Banpresto',
	 'Zoro trạng thái chiến đấu tại Dressrosa: tư thế vung kiếm dứt khoát, cơ bắp rắn chắc và nước sơn mịn, bền màu.',
	 '/uploads/Figure/Figure8.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1209','One Piece – Bonney Girls Collection Vol.2 Ichiban Kuji Figure genuine Banpresto',
	 'Jewelry Bonney trong dòng Girls Collection Vol.2: tạo dáng gợi cảm, màu sắc tươi và các chi tiết phụ kiện tinh xảo.',
	 '/uploads/Figure/Figure9.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1210','One Piece – Boa Hancock Glitter & Glamours Figure genuine Bandai',
	 'Boa Hancock dòng Glitter & Glamours với tỷ lệ chuẩn, nếp váy và tóc đổ mượt; phù hợp trưng bày cùng bộ sưu tập One Piece.',
	 '/uploads/Figure/Figure10.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1211','Premium One Piece – Sabo Premium Figure The Metallic Genuine Bandai',
	 'Sabo phiên bản Metallic hoàn thiện lớp sơn ánh kim ấn tượng; tạo dáng mạnh mẽ, phù hợp làm điểm nhấn tủ trưng bày.',
	 '/uploads/Figure/Figure11.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),


-- LSP13 Calculator
	('SP1301','Casio fx-880BTG-BK',
	 'Premium scientific calculator in elegant black with Bluetooth connectivity, Natural Textbook Display, and fast calculations — ideal for students and professionals.',
	 '/uploads/Calculator/Calculator1.jpg',
	 'Casio','Casio', TRUE, 'LSP13', NULL),
	
	('SP1302','Casio fx-880BTG-BU',
	 'Blue edition of the fx-880BTG featuring Bluetooth pairing to the Casio EDU app, intuitive keys, Natural Textbook Display, and robust exam-ready functions.',
	 '/uploads/Calculator/Calculator2.jpg',
	 'Casio','Casio', TRUE, 'LSP13', NULL),
	
	('SP1303','Casio fx-880BTG-PK',
	 'Pink edition of the fx-880BTG with Bluetooth connectivity, quick computation, and an easy-to-read Natural Textbook Display for modern learners.',
	 '/uploads/Calculator/Calculator3.jpg',
	 'Casio','Casio', TRUE, 'LSP13', NULL),
	
	('SP1304','Casio fx-880BTG-GY',
	 'Gray edition of the fx-880BTG offering reliable scientific features, Bluetooth support, and a durable build for everyday classroom or exam use.',
	 '/uploads/Calculator/Calculator4.jpg',
	 'Casio','Casio', TRUE, 'LSP13', NULL),
	
	('SP1305','Casio fx-9860GIII',
	 'Advanced graphing calculator with high-resolution display, powerful graph/solve/statistics tools, USB connectivity, and ample storage for classroom applications.',
	 '/uploads/Calculator/Calculator5.jpg',
	 'Casio','Casio', TRUE, 'LSP13', NULL),


-- LSP14 Note
	('SP1401','Note Great ideas start here',
	 'A minimalist notebook designed to capture your best thoughts and creative ideas. Smooth paper and elegant cover make it perfect for everyday journaling.',
	 '/uploads/Note/Note1.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP14', NULL),
	
	('SP1402','Note Basic Green',
	 'A refreshing green-themed notebook that inspires calm and focus. Ideal for studying, planning, or jotting down your daily inspirations.',
	 '/uploads/Note/Note2.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP14', NULL),
	
	('SP1403','Note Neen Dream',
	 'A dreamy pastel notebook that sparks imagination and positivity. Compact and stylish—your perfect creative companion.',
	 '/uploads/Note/Note3.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP14', NULL),
	
	('SP1404','Note I Love Aventure',
	 'A travel-inspired notebook for adventure lovers. Capture memories, plans, and dreams wherever your journey takes you.',
	 '/uploads/Note/Note4.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP14', NULL),
	
	('SP1405','Note Chill',
	 'A simple yet modern notebook with a relaxed vibe. Great for daily notes, sketches, or journaling your chill moments.',
	 '/uploads/Note/Note5.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP14', NULL),
	
	('SP1406','Note L24',
	 'A clean and minimal design notebook for productivity and clarity. Durable binding and quality pages for everyday use.',
	 '/uploads/Note/Note6.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP14', NULL),
	
	('SP1407','Note Live Your Dream',
	 'Motivational notebook with an inspiring quote on the cover. Perfect for goal-setting, journaling, and chasing your ambitions.',
	 '/uploads/Note/Note7.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP14', NULL),
	
	('SP1408','Note Basic',
	 'A simple and functional notebook that fits any occasion. Lightweight and versatile for school, office, or personal notes.',
	 '/uploads/Note/Note8.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP14', NULL),
	
	('SP1409','Note Connor',
	 'A stylish notebook with a modern, clean cover design. Ideal for professionals or students who appreciate elegant simplicity.',
	 '/uploads/Note/Note9.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP14', NULL),
	
	('SP1410','Note Cat Chibi',
	 'A cute and fun notebook featuring adorable chibi cat art. Brings joy to note-taking and makes a perfect gift for cat lovers.',
	 '/uploads/Note/Note10.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP14', NULL),


-- LSP15 Watch
	('SP1501','CASIO AE-1500WHC-1AV',
	 'Large easy-to-read digital display with 10-year battery life, 100 m water resistance, LED light, stopwatch, timer, and daily alarms — black resin band/bezel.',
	 '/uploads/Watch/Watch1.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	('SP1502','CASIO AE-1500WHC-8AV',
	 'Same AE-1500 features — oversized screen, 10-year battery, 100 m water resistance, LED light, stopwatch/timer/alarms — in the gray/8AV colorway.',
	 '/uploads/Watch/Watch2.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	-- AE-1700H: world time, 10 năm pin
	('SP1503','CASIO AE-1700H-1BV',
	 'World Time digital with 10-year battery, 100 m water resistance, LED light, 5 alarms, stopwatch, countdown timer, and world map display — 1BV black variant.',
	 '/uploads/Watch/Watch3.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	('SP1504','CASIO AE-1700H-1AV',
	 'AE-1700 series: World Time, 10-year battery, 100 m water resistance, LED light, 5 alarms, stopwatch and timer — classic 1AV black finish.',
	 '/uploads/Watch/Watch4.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	('SP1505','CASIO AE-1700H-1A2V',
	 'AE-1700 World Time with blue accent (1A2V): 10-year battery, 100 m water resistance, LED light, 5 alarms, stopwatch, and countdown timer.',
	 '/uploads/Watch/Watch5.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	-- AQ-230EM: retro ana-digi, dây thép
	('SP1506','CASIO AQ-230EM-7A',
	 'Retro ana-digi with minimalist silver dial, stainless-steel mesh band, dual time, daily alarm, stopwatch, auto calendar, and 30 m water resistance.',
	 '/uploads/Watch/Watch6.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	('SP1507','CASIO AQ-230EM-2A',
	 'Classic rectangular ana-digi in navy/blue tone with stainless-steel mesh band, dual time, alarm, stopwatch, auto calendar, and 30 m water resistance.',
	 '/uploads/Watch/Watch7.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	-- A158WEM: vintage digital, dây lưới thép
	('SP1508','CASIO A158WEM-7',
	 'Iconic vintage digital with stainless-steel mesh band, LED light, stopwatch, daily alarm, auto calendar, and water resistance — silver dial (-7).',
	 '/uploads/Watch/Watch8.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	('SP1509','CASIO A158WEM-3',
	 'Classic A158 with mesh band and green-tone dial (-3): LED light, stopwatch, daily alarm, auto calendar, and water resistance in a slim retro case.',
	 '/uploads/Watch/Watch9.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	-- MWD-100HD: digital 100m WR, dây thép
	('SP1510','CASIO MWD-100HD-1BV',
	 'Rugged digital with stainless-steel band, 100 m water resistance, oversized LCD, LED light, 5 alarms, stopwatch, countdown timer, and full auto calendar.',
	 '/uploads/Watch/Watch10.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	-- AQ-240E: mỏng, chữ nhật, ana-digi
	('SP1511','CASIO AQ-240E-3A',
	 'Slim rectangular ana-digi with green dial accents, dual time, daily alarm, hourly signal, stopwatch, and 30 m water resistance — elegant retro styling.',
	 '/uploads/Watch/Watch11.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),


-- LSP16 Pen
	('SP1601','Parker IM Premium Grey BT Rollerball Pen GB4-2127925',
	 'A refined premium rollerball pen featuring a matte grey finish and black trim. Smooth ink flow and elegant design make it perfect for professionals.',
	 '/uploads/Pen/Pen1.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1602','Parker Sonnet Lacque Red GT 18K Fountain Pen TB-1950774',
	 'An exquisite red lacquer fountain pen with 18K gold nib and gold trim. The Parker Sonnet combines timeless elegance with exceptional writing performance.',
	 '/uploads/Pen/Pen2.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1603','Parker Sonnet SE18 Pink CT Fine Rollerball Pen GB-2054824',
	 'A special edition Parker Sonnet rollerball pen with a sleek pink lacquer finish and chrome trim. Delivers precision and sophistication in every stroke.',
	 '/uploads/Pen/Pen3.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1604','Parker IM Premium Warm Grey GT Fountain Pen TB4-1975628',
	 'A premium fountain pen featuring a warm grey body with gold accents. Stylish and durable, ideal for daily writing or gift giving.',
	 '/uploads/Pen/Pen4.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1605','Parker IM Premium Black CT Rollerball Pen TB4-1975584',
	 'A modern rollerball pen from the Parker IM Premium line, showcasing a sleek matte black finish with chrome trim. Perfect blend of design and comfort.',
	 '/uploads/Pen/Pen5.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1606','Allan D’lious 0502RR Rollerball Pen',
	 'A stylish Allan D’lious rollerball pen featuring a balanced body and smooth ink delivery—perfect for business professionals and daily note-taking.',
	 '/uploads/Pen/Pen6.jpg',
	 'Allan D’lious','Allan D’lious', TRUE, 'LSP16', NULL),
	
	('SP1607','Lamy Al-Star Pacific Special Edition 2017 Fountain Pen',
	 'A limited-edition fountain pen in a vibrant Pacific blue aluminum finish. Lightweight, durable, and designed for smooth, expressive writing.',
	 '/uploads/Pen/Pen7.jpg',
	 'Lamy','Lamy', TRUE, 'LSP16', NULL),
	
	('SP1608','Allan D’lious AG0302BBK Gold-Plated Ballpoint Pen',
	 'An elegant gold-plated ballpoint pen with premium finish and durable twist mechanism. Combines luxury and everyday practicality.',
	 '/uploads/Pen/Pen8.jpg',
	 'Allan D’lious','Allan D’lious', TRUE, 'LSP16', NULL),
	
	('SP1609','Montblanc Meisterstück UNICEF Solitaire LeGrand Rollerball MB116084',
	 'A luxurious Montblanc Meisterstück LeGrand rollerball pen created in support of UNICEF. Crafted with exquisite detailing and timeless sophistication.',
	 '/uploads/Pen/Pen9.jpg',
	 'Montblanc','Montblanc', TRUE, 'LSP16', NULL),
	
	('SP1610','Parker IM Monochrome Blue Rollerball Pen 2172965',
	 'A sleek all-blue rollerball pen from the Parker IM Monochrome series. Minimalist design meets smooth performance for modern professionals.',
	 '/uploads/Pen/Pen10.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1611','Parker JOT SS GT FP M BLU GB 2030948 Fountain Pen',
	 'Parker Jotter Stainless Steel Gold Trim fountain pen, medium nib. Smooth ink flow, reliable snap cap, and gift box—everyday elegance.',
	 '/uploads/Pen/Pen11.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1612','Parker Jotter Ballpoint Pen D-WTL Blue CT TB6-1953411',
	 'Iconic Parker Jotter ballpoint in Blue with chrome trim. Click action, durable stainless barrel, and smooth Quinkflow refill.',
	 '/uploads/Pen/Pen12.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1613','Parker IM SE GB4 Ballpoint Pen – 2074161',
	 'Parker IM Special Edition ballpoint with balanced weight and refined finish. Consistent, clean lines for daily writing and gifting.',
	 '/uploads/Pen/Pen13.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1614','Parker JOT D-STAIN Steel CT Ballpoint Pen TB6-1953344',
	 'Jotter Stainless Steel ballpoint with chrome trim. Classic click mechanism, scratch-resistant body, and crisp, dependable writing.',
	 '/uploads/Pen/Pen14.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1615','Parker Ballpoint Pen Sonnet Slim Black CT TB-1950882',
	 'Parker Sonnet Slim ballpoint in Black with chrome trim. Sleek profile, precise twist action, and premium refill for effortless notes.',
	 '/uploads/Pen/Pen15.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1616','Ballpoint Pen Parker JOT SE London CT 2221608 (Red)',
	 'Jotter Special Edition "London" ballpoint in Red, chrome trim. City-inspired design with the fast, reliable Jotter click.',
	 '/uploads/Pen/Pen16.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1617','Parker JOT SE Miami CT 2221607 Ballpoint Pen (Turquoise)',
	 'Jotter Special Edition "Miami" ballpoint in Turquoise with chrome trim. Vibrant, modern look plus smooth Parker Quinkflow performance.',
	 '/uploads/Pen/Pen17.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),



-- LSP17 Draw
	('SP1701','Colokit WACO-C001',
	 'Set of 5 versatile paint brushes with soft bristles and smooth paint pickup. Ideal for acrylic, watercolor, and poster color.',
	 '/uploads/Draw/Draw1.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1702','Colokit WACO-C002/DO',
	 'Set of 48 fine-tip markers with vibrant, long-lasting colors. Great for sketching, lettering, and detailed work on paper or canvas.',
	 '/uploads/Draw/Draw2.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1703','Colokit CPC-C06',
	 'Acrylic paint markers with steady ink flow and strong coverage. Perfect for crafts, calligraphy, and quick decorations on multiple surfaces.',
	 '/uploads/Draw/Draw3.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1704','Colokit CPC-C08 16-color pencil',
	 'Box of 16 colored pencils with durable cores and smooth shading. Easy to blend for study, doodling, and everyday creativity.',
	 '/uploads/Draw/Draw4.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1705','Thien Long Colokit ART MARKER AM-C001 – 24-color markers',
	 'Set of 24 vivid markers that dry quickly and resist smudging. Suited for illustration, posters, and decorative note-taking.',
	 '/uploads/Draw/Draw5.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1706','Colokit Doraemon TCR-C04/DO – 12-color twisting wax pen',
	 '12 twistable wax crayons—no sharpening needed. Smooth strokes, low mess, great for kids’ coloring and school projects.',
	 '/uploads/Draw/Draw6.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1707','Thien Long Colokit TCR-C007/DO – 12-color twisted wax pen',
	 '12 twist wax pens with sturdy barrels and even color laydown. Works well on drawing paper, notebooks, and craft items.',
	 '/uploads/Draw/Draw7.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1708','Colokit Doraemon TCR-C05/DO – 18-color twisting wax pen',
	 '18 rich twistable crayons offering wide color variety for bold, playful artwork and classroom activities.',
	 '/uploads/Draw/Draw8.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1709','DIY Wooden Painting Set – Thien Long Transport Colokit KIT-C032',
	 'DIY wooden painting kit themed around vehicles. Includes pre-cut wooden pieces and paints—fun for learning and creativity.',
	 '/uploads/Draw/Draw9.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1710','Thien Long Colokit TCR-C008/DO – 18-color twisted wax pen',
	 '18 twist wax pens with smooth, consistent color. Handy, clean, and ideal for everyday coloring and craft projects.',
	 '/uploads/Draw/Draw10.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),



-- LSP18 Studentbook
	('SP1801','Bear Lined Notebook',
	 'A cute and friendly Bear-themed lined notebook designed for students. Smooth paper, durable cover, and perfect for daily notes or doodles.',
	 '/uploads/Studentbook/Studentbook1.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP18', NULL),
	
	('SP1802','Campus KAWAII Notebook 1',
	 'A stylish Campus KAWAII notebook featuring adorable pastel designs. Great for journaling, studying, or creative writing.',
	 '/uploads/Studentbook/Studentbook2.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP18', NULL),
	
	('SP1803','Campus KAWAII Notebook 2',
	 'Another charming KAWAII edition notebook with bright, cheerful artwork. Brings fun and inspiration to your study time.',
	 '/uploads/Studentbook/Studentbook3.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP18', NULL),
	
	('SP1804','Sao Mai “Keep Calm” Lined Notebook',
	 'A motivational “Keep Calm” notebook from Sao Mai with soft pages and a modern design—ideal for school or personal planning.',
	 '/uploads/Studentbook/Studentbook4.jpg',
	 'Sao Mai','Sao Mai', TRUE, 'LSP18', NULL),
	
	('SP1805','Sao Mai “Emotion” Lined Notebook',
	 'A Sao Mai Emotion notebook with expressive cover art. Perfect for capturing your thoughts, lessons, and creative ideas.',
	 '/uploads/Studentbook/Studentbook5.jpg',
	 'Sao Mai','Sao Mai', TRUE, 'LSP18', NULL),
	
	('SP1806','School Joyful Notebook',
	 'A bright and playful notebook designed to bring joy to studying. Sturdy cover and smooth paper for comfortable writing.',
	 '/uploads/Studentbook/Studentbook6.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP18', NULL),
	
	('SP1807','Class Minions Notebook',
	 'A fun and colorful Minions-themed notebook that makes studying more enjoyable. Great for students who love creative stationery.',
	 '/uploads/Studentbook/Studentbook7.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP18', NULL),
	
	('SP1808','Pupil “New Wave” Lined Notebook',
	 'A modern-style notebook from Pupil with a vibrant New Wave cover. Combines trendy design with quality paper for everyday use.',
	 '/uploads/Studentbook/Studentbook8.jpg',
	 'Pupil','Pupil', TRUE, 'LSP18', NULL),
	
	('SP1809','Subject Notebook',
	 'A simple, functional Subject notebook with ruled pages. Ideal for organizing notes by subject or class.',
	 '/uploads/Studentbook/Studentbook9.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP18', NULL),
	
	('SP1810','Study Minions Lined Notebook',
	 'A cheerful lined notebook with cute Minions cover design. Perfect for school notes, sketches, and creative journaling.',
	 '/uploads/Studentbook/Studentbook10.jpg',
	 'Various Brands','Various Publishers', TRUE, 'LSP18', NULL),



-- LSP19 CompaEke
	('SP1901','Hong Ha compass (3215), student compass, pencil compass, iron compass, cute compass',
	 'Durable, precise school compass with smooth adjustment wheel and firm needle grip—great for accurate circles in class or technical drawing.',
	 '/uploads/CompaEke/CompaEke1.jpg',
	 'Hong Ha','Hong Ha', TRUE, 'LSP19', NULL),
	
	('SP1902','Compa Thien Long C-015',
	 'Modern compass with built-in mechanical pencil and fine hinge control—ideal for neat, precise geometry work.',
	 '/uploads/CompaEke/CompaEke2.jpg',
	 'Thien Long','Thien Long', TRUE, 'LSP19', NULL),
	
	('SP1903','Compa Thien Long C-017',
	 'All-metal compass with micro-adjust knob and anti-slip needle. Includes spare lead for consistent, exact arcs.',
	 '/uploads/CompaEke/CompaEke3.jpg',
	 'Thien Long','Thien Long', TRUE, 'LSP19', NULL),
	
	('SP1904','Eke Set – 4 Pieces',
	 'Complete geometry kit: 30°/60° and 45° set squares, 20 cm ruler, and 180° protractor. Clear acrylic with easy-to-read mm scale.',
	 '/uploads/CompaEke/CompaEke4.jpg',
	 'Eke','Eke', TRUE, 'LSP19', NULL),
	
	('SP1905','Compa Thien Long C-016',
	 'Lightweight student compass with safety cap and smooth hinge. Fits standard 2 mm leads; draws clean circles reliably.',
	 '/uploads/CompaEke/CompaEke5.jpg',
	 'Thien Long','Thien Long', TRUE, 'LSP19', NULL),
	
	('SP1906','Compa Thien Long C-018',
	 'Premium compass with extension bar support for larger diameters, stable center pin, and steady lead pressure.',
	 '/uploads/CompaEke/CompaEke6.jpg',
	 'Thien Long','Thien Long', TRUE, 'LSP19', NULL),
	
	('SP1907','Point 10 MTEN compass set TP-C019',
	 'Pastel compass set in a compact case—includes spare leads and accessories. Cute look, dependable precision.',
	 '/uploads/CompaEke/CompaEke7.jpg',
	 'MTEN (Point 10)','MTEN (Point 10)', TRUE, 'LSP19', NULL),
	
	('SP1908','Thien Long Y C-020 compass set',
	 'Versatile compass set with extra lead and classroom-ready case. Built for smooth adjustments and accurate drafting.',
	 '/uploads/CompaEke/CompaEke8.jpg',
	 'Thien Long','Thien Long', TRUE, 'LSP19', NULL),
	
	('SP1909','Compa MTEN Point 10 TP-C012 TSTT4 Elsa',
	 'Kid-friendly themed compass with protected needle, smooth hinge, and snug case—fun design that still draws precise circles.',
	 '/uploads/CompaEke/CompaEke9.jpg',
	 'MTEN (Point 10)','MTEN (Point 10)', TRUE, 'LSP19', NULL),
	
	('SP1910','Compa MTEN Point 10 TP-C012',
	 'Everyday student compass with reliable locking, steady lead pressure, and compact storage case for school use.',
	 '/uploads/CompaEke/CompaEke10.jpg',
	 'MTEN (Point 10)','MTEN (Point 10)', TRUE, 'LSP19', NULL),

-- LSP20 PencilEraser
	('SP2001','Clear Rotation PLUS Pokemon - Dimensions 66 x 21 x 13 mm',
	 'Compact PLUS Pokémon-themed eraser in a clear rotating sleeve. Clean erasing with low residue; pocket-friendly size 66×21×13 mm.',
	 '/uploads/PencilEraser/PencilEraser1.jpg',
	 'PLUS','PLUS', TRUE, 'LSP20', NULL),
	
	('SP2002','Staedtler 52635N lead eraser',
	 'STAEDTLER vinyl eraser for graphite pencils. Smooth, smudge-free erasing with minimal crumbs on most paper types.',
	 '/uploads/PencilEraser/PencilEraser2.jpg',
	 'STAEDTLER','STAEDTLER', TRUE, 'LSP20', NULL),
	
	('SP2003','Thien Long car lead eraser TP-E020/CA',
	 'Novelty car-shaped eraser by Thien Long. Soft formula erases cleanly—fun and safe for school use.',
	 '/uploads/PencilEraser/PencilEraser3.jpg',
	 'Thien Long','Thien Long', TRUE, 'LSP20', NULL),
	
	('SP2004','Staedtler 526 E40 Lead Eraser - Small black',
	 'Small black STAEDTLER eraser with firm edges for precise corrections. Low-dust performance.',
	 '/uploads/PencilEraser/PencilEraser4.jpg',
	 'STAEDTLER','STAEDTLER', TRUE, 'LSP20', NULL),
	
	('SP2005','Pentel ZEH-05SP pencil eraser - light pink',
	 'Pentel Hi-Polymer eraser (ZEH-05SP) in light pink casing. Gentle on paper, highly effective on graphite.',
	 '/uploads/PencilEraser/PencilEraser5.jpg',
	 'Pentel','Pentel', TRUE, 'LSP20', NULL),
	
	('SP2006','Pentel ZEH05 white pencil eraser (missile tablet)',
	 'Classic Pentel Hi-Polymer ZEH-05 white eraser. Soft touch, clean lift, and durable block form.',
	 '/uploads/PencilEraser/PencilEraser6.jpg',
	 'Pentel','Pentel', TRUE, 'LSP20', NULL),
	
	('SP2007','Pentel ZEH-05S multi-colored pencil eraser (missile)',
	 'Pentel ZEH-05S multi-color series. Reliable Hi-Polymer compound with crisp corners for detailed erasing.',
	 '/uploads/PencilEraser/PencilEraser7.jpg',
	 'Pentel','Pentel', TRUE, 'LSP20', NULL),
	
	('SP2008','Genuine Plus pencil eraser, small black Pokemon and Pikachu tablets',
	 'Official PLUS small black Pokémon/Pikachu eraser. Neat erasing in a compact, collectible block.',
	 '/uploads/PencilEraser/PencilEraser8.jpg',
	 'PLUS','PLUS', TRUE, 'LSP20', NULL),
	
	('SP2009','Staedtler 526 35B black pencil eraser',
	 'STAEDTLER 526 35B black eraser—dust-reduced, smear-resistant, and suitable for everyday writing or sketching.',
	 '/uploads/PencilEraser/PencilEraser9.jpg',
	 'STAEDTLER','STAEDTLER', TRUE, 'LSP20', NULL),
	
	('SP2010','Genuine Plus pencil eraser, Pokemon and Pikachu yellow tablets',
	 'Official PLUS small yellow Pokémon/Pikachu eraser block. Soft, clean erase with a playful theme.',
	 '/uploads/PencilEraser/PencilEraser10.jpg',
	 'PLUS','PLUS', TRUE, 'LSP20', NULL);




-- =========================
-- INITIAL IMPORT INVOICE
-- =========================
INSERT INTO import_invoice (import_invoice_code, discount, total_amount, description, employee_code, supplier_code, status)
VALUES ('PN01', 0, 2500000, 'Phiếu nhập lần đầu toàn bộ sách', 'NV_KETOAN', 'NCC01', 'APPROVED');


-- =========================
-- IMPORT INVOICE DETAILS (50 PRODUCTS)
-- ========================
INSERT INTO import_invoice_detail (import_invoice_detail_code, quantity_imported, import_price, total_amount, product_code, import_invoice_code) VALUES
    -- LSP01 Âm nhạc
    ('CTPN001', 10, 50000, 500000, 'SP0101','PN01'),
    ('CTPN002', 10, 50000, 500000, 'SP0102','PN01'),
    ('CTPN003', 10, 50000, 500000, 'SP0103','PN01'),
    ('CTPN004', 10, 50000, 500000, 'SP0104','PN01'),
    ('CTPN005', 10, 50000, 500000, 'SP0105','PN01');




-- =========================
-- INVENTORY SEED (create inventory rows for existing products)
-- =========================
-- For each product without an inventory row, create one with inventory_code = 'INV_<product_code>'
INSERT INTO inventory (inventory_code, status, product_code, created_date, updated_date)
SELECT CONCAT('INV_', p.product_code) AS inventory_code,
	   TRUE AS status,
	   p.product_code,
	   NOW() AS created_date,
	   NOW() AS updated_date
FROM product p
LEFT JOIN inventory i ON i.product_code = p.product_code
WHERE i.id IS NULL;

-- =========================
-- INVENTORY DETAIL
-- =========================

INSERT INTO inventory_detail (inventory_detail_code, inventory_code, import_invoice_code, import_invoice_detail_code, quantity) VALUES
('INVDET_demo01','INV_SP0101','PN01','CTPN001',10),
('INVDET_demo02','INV_SP0102','PN01','CTPN002',10),
('INVDET_demo03','INV_SP0103','PN01','CTPN003',10),
('INVDET_demo04','INV_SP0104','PN01','CTPN004',10),
('INVDET_demo05','INV_SP0105','PN01','CTPN005',10);





INSERT INTO price_history (price_history_code, unit_price, product_code, import_invoice_code, import_invoice_detail_code) VALUES
-- LSP01 Romance
('LSG001A',75000,'SP0101',"PN01",'CTPN001'),
('LSG002A',68000,'SP0102',"PN01",'CTPN002'),
('LSG003A',52000,'SP0103',"PN01",'CTPN003'),
('LSG004A',63000,'SP0104',"PN01",'CTPN004'),
('LSG005A',71000,'SP0105',"PN01",'CTPN005');



-- =========================
-- UPDATE PRODUCTS WITH AUTHOR AND PUBLISHER
-- =========================
-- LSP01 Romance
-- UPDATE product SET author = 'Colleen Hoover', publisher = 'Atria Books' WHERE product_code = 'SP0101';
-- UPDATE product SET author = 'Colleen Hoover', publisher = 'Atria Books' WHERE product_code = 'SP0102';
-- UPDATE product SET author = 'Kate Stewart', publisher = 'St. Martin''s Press' WHERE product_code = 'SP0103';
-- UPDATE product SET author = 'Kristin Hannah', publisher = 'St. Martin''s Press' WHERE product_code = 'SP0104';
-- UPDATE product SET author = 'Colleen Hoover', publisher = 'Atria Books' WHERE product_code = 'SP0105';
-- UPDATE product SET author = 'Eva Pohler', publisher = 'Independently Published' WHERE product_code = 'SP0106';
-- UPDATE product SET author = 'Sangu Manga', publisher = 'HQ' WHERE product_code = 'SP0107';
-- UPDATE product SET author = 'Taylor Jenkins Reid', publisher = 'Atria Books' WHERE product_code = 'SP0108';
-- UPDATE product SET author = 'Julie Soto', publisher = 'Atria Books' WHERE product_code = 'SP0109';
-- UPDATE product SET author = 'Alex Aster', publisher = 'Sourcebooks' WHERE product_code = 'SP0110';

-- -- LSP02 Horror
-- UPDATE product SET author = 'Tananarive Due', publisher = 'Saga Press' WHERE product_code = 'SP0201';
-- UPDATE product SET author = 'Mark Z. Danielewski', publisher = 'Pantheon' WHERE product_code = 'SP0202';
-- UPDATE product SET author = 'Mary Shelley', publisher = 'Penguin Classics' WHERE product_code = 'SP0203';
-- UPDATE product SET author = 'Riley Sager', publisher = 'Dutton' WHERE product_code = 'SP0204';
-- UPDATE product SET author = 'Laura Thalassa', publisher = 'Independently Published' WHERE product_code = 'SP0205';
-- UPDATE product SET author = 'Stephen King', publisher = 'Hodder & Stoughton' WHERE product_code = 'SP0206';
-- UPDATE product SET author = 'Stephen King', publisher = 'Viking Press' WHERE product_code = 'SP0207';
-- UPDATE product SET author = 'Kate Alice Marshall', publisher = 'Greenwillow Books' WHERE product_code = 'SP0208';
-- UPDATE product SET author = 'Roald Dahl', publisher = 'Farrar, Straus and Giroux' WHERE product_code = 'SP0209';
-- UPDATE product SET author = 'Shirley Jackson', publisher = 'Penguin Classics' WHERE product_code = 'SP0210';

-- -- LSP03 Fantasy
-- UPDATE product SET author = 'Matt Dinniman', publisher = 'Independently Published' WHERE product_code = 'SP0301';
-- UPDATE product SET author = 'J.R.R. Tolkien', publisher = 'Houghton Mifflin Harcourt' WHERE product_code = 'SP0302';
-- UPDATE product SET author = 'Paulo Coelho', publisher = 'HarperOne' WHERE product_code = 'SP0303';
-- UPDATE product SET author = 'Sarah J. Maas', publisher = 'Bloomsbury' WHERE product_code = 'SP0304';
-- UPDATE product SET author = 'Sarah J. Maas', publisher = 'Bloomsbury' WHERE product_code = 'SP0305';
-- UPDATE product SET author = 'Sarah J. Maas', publisher = 'Bloomsbury' WHERE product_code = 'SP0306';
-- UPDATE product SET author = 'Sarah J. Maas', publisher = 'Bloomsbury' WHERE product_code = 'SP0307';
-- UPDATE product SET author = 'Sarah J. Maas', publisher = 'Bloomsbury' WHERE product_code = 'SP0308';
-- UPDATE product SET author = 'Sarah J. Maas', publisher = 'Bloomsbury' WHERE product_code = 'SP0309';
-- UPDATE product SET author = 'Sarah J. Maas', publisher = 'Bloomsbury' WHERE product_code = 'SP0310';

-- -- LSP04 Business
-- UPDATE product SET author = 'Mel Robbins', publisher = 'Portfolio' WHERE product_code = 'SP0401';
-- UPDATE product SET author = 'James Clear', publisher = 'Avery' WHERE product_code = 'SP0402';
-- UPDATE product SET author = 'Chris Voss', publisher = 'Houghton Mifflin Harcourt' WHERE product_code = 'SP0403';
-- UPDATE product SET author = 'Napoleon Hill', publisher = 'Tarcher/Penguin' WHERE product_code = 'SP0404';
-- UPDATE product SET author = 'Stephen Covey', publisher = 'Simon & Schuster' WHERE product_code = 'SP0405';
-- UPDATE product SET author = 'Simon Sinek', publisher = 'Portfolio' WHERE product_code = 'SP0406';
-- UPDATE product SET author = 'Daniel Pink', publisher = 'Riverhead' WHERE product_code = 'SP0407';
-- UPDATE product SET author = 'Marshall Goldsmith', publisher = 'Hyperion' WHERE product_code = 'SP0408';
-- UPDATE product SET author = 'Charles Duhigg', publisher = 'Random House' WHERE product_code = 'SP0409';
-- UPDATE product SET author = 'Jim Collins', publisher = 'HarperBusiness' WHERE product_code = 'SP0410';

-- -- LSP05 Drama
-- UPDATE product SET author = 'William Shakespeare', publisher = 'Dover' WHERE product_code = 'SP0501';
-- UPDATE product SET author = 'Anton Chekhov', publisher = 'Dover Publications' WHERE product_code = 'SP0502';
-- UPDATE product SET author = 'Henrik Ibsen', publisher = 'Dover' WHERE product_code = 'SP0503';
-- UPDATE product SET author = 'Arthur Miller', publisher = 'Penguin Classics' WHERE product_code = 'SP0504';
-- UPDATE product SET author = 'Tennessee Williams', publisher = 'Signet' WHERE product_code = 'SP0505';
-- UPDATE product SET author = 'Oscar Wilde', publisher = 'Penguin Classics' WHERE product_code = 'SP0506';
-- UPDATE product SET author = 'Molière', publisher = 'Dover' WHERE product_code = 'SP0507';
-- UPDATE product SET author = 'Sophocles', publisher = 'Oxford University Press' WHERE product_code = 'SP0508';
-- UPDATE product SET author = 'Lorraine Hansberry', publisher = 'Vintage Books' WHERE product_code = 'SP0509';
-- UPDATE product SET author = 'Bertolt Brecht', publisher = 'Methuen' WHERE product_code = 'SP0510';

-- -- LSP06 Biography
-- UPDATE product SET author = 'Viktor Frankl', publisher = 'Beacon Press' WHERE product_code = 'SP0601';
-- UPDATE product SET author = 'Malcolm X', publisher = 'Grove Press' WHERE product_code = 'SP0602';
-- UPDATE product SET author = 'Anne Frank', publisher = 'Doubleday' WHERE product_code = 'SP0603';
-- UPDATE product SET author = 'Steve Jobs', publisher = 'Simon & Schuster' WHERE product_code = 'SP0604';
-- UPDATE product SET author = 'Sheryl Sandberg', publisher = 'Knopf' WHERE product_code = 'SP0605';
-- UPDATE product SET author = 'Marie Curie', publisher = 'Dover' WHERE product_code = 'SP0606';
-- UPDATE product SET author = 'Nelson Mandela', publisher = 'Little, Brown' WHERE product_code = 'SP0607';
-- UPDATE product SET author = 'Audrey Hepburn', publisher = 'Gallery Books' WHERE product_code = 'SP0608';
-- UPDATE product SET author = 'Malala Yousafzai', publisher = 'Weidenfeld & Nicolson' WHERE product_code = 'SP0609';
-- UPDATE product SET author = 'Muhammad Ali', publisher = 'Penguin' WHERE product_code = 'SP0610';

-- -- LSP07 Cook
-- UPDATE product SET author = 'Gordon Ramsay', publisher = 'HQ' WHERE product_code = 'SP0701';
-- UPDATE product SET author = 'Jamie Oliver', publisher = 'Penguin' WHERE product_code = 'SP0702';
-- UPDATE product SET author = 'Julia Child', publisher = 'Knopf' WHERE product_code = 'SP0703';
-- UPDATE product SET author = 'Martha Stewart', publisher = 'Clarkson Potter' WHERE product_code = 'SP0704';
-- UPDATE product SET author = 'Alice Waters', publisher = 'Random House' WHERE product_code = 'SP0705';
-- UPDATE product SET author = 'Thomas Keller', publisher = 'Artisan' WHERE product_code = 'SP0706';
-- UPDATE product SET author = 'Wolfgang Puck', publisher = 'Knopf' WHERE product_code = 'SP0707';
-- UPDATE product SET author = 'Bobby Flay', publisher = 'Clarkson Potter' WHERE product_code = 'SP0708';
-- UPDATE product SET author = 'Rachael Ray', publisher = 'Lake Isle Press' WHERE product_code = 'SP0709';
-- UPDATE product SET author = 'Emeril Lagasse', publisher = 'William Morrow' WHERE product_code = 'SP0710';

-- -- LSP08 Poetry
-- UPDATE product SET author = 'Anonymous', publisher = 'Dover' WHERE product_code = 'SP0801';
-- UPDATE product SET author = 'Emily Dickinson', publisher = 'Penguin Classics' WHERE product_code = 'SP0802';
-- UPDATE product SET author = 'Robert Frost', publisher = 'Holt' WHERE product_code = 'SP0803';
-- UPDATE product SET author = 'Maya Angelou', publisher = 'Random House' WHERE product_code = 'SP0804';
-- UPDATE product SET author = 'Sylvia Plath', publisher = 'Harper Perennial' WHERE product_code = 'SP0805';
-- UPDATE product SET author = 'Pablo Neruda', publisher = 'Grove Press' WHERE product_code = 'SP0806';
-- UPDATE product SET author = 'Rainer Maria Rilke', publisher = 'Dover' WHERE product_code = 'SP0807';
-- UPDATE product SET author = 'W.H. Auden', publisher = 'Random House' WHERE product_code = 'SP0808';
-- UPDATE product SET author = 'Mary Oliver', publisher = 'Penguin' WHERE product_code = 'SP0809';
-- UPDATE product SET author = 'Billy Collins', publisher = 'Random House' WHERE product_code = 'SP0810';

-- -- LSP09 Art
-- UPDATE product SET author = 'Michael Finkel', publisher = 'Knopf' WHERE product_code = 'SP0901';
-- UPDATE product SET author = 'E.H. Gombrich', publisher = 'Phaidon' WHERE product_code = 'SP0902';
-- UPDATE product SET author = 'Wassily Kandinsky', publisher = 'Dover' WHERE product_code = 'SP0903';
-- UPDATE product SET author = 'Andy Warhol', publisher = 'Penguin' WHERE product_code = 'SP0904';
-- UPDATE product SET author = 'David Hockney', publisher = 'Thames & Hudson' WHERE product_code = 'SP0905';
-- UPDATE product SET author = 'Picasso', publisher = 'Taschen' WHERE product_code = 'SP0906';
-- UPDATE product SET author = 'Frida Kahlo', publisher = 'Calmann-Lévy' WHERE product_code = 'SP0907';
-- UPDATE product SET author = 'Salvador Dalí', publisher = 'Taschen' WHERE product_code = 'SP0908';
-- UPDATE product SET author = 'Joan Mitchell', publisher = 'Phaidon' WHERE product_code = 'SP0909';
-- UPDATE product SET author = 'Marina Abramović', publisher = 'Penguin' WHERE product_code = 'SP0910';

-- -- LSP10 Architecture
-- UPDATE product SET author = 'Marianne Cusato', publisher = 'Storey Publishing' WHERE product_code = 'SP1001';
-- UPDATE product SET author = 'Frank Lloyd Wright', publisher = 'Dover' WHERE product_code = 'SP1002';
-- UPDATE product SET author = 'Le Corbusier', publisher = 'Dover' WHERE product_code = 'SP1003';
-- UPDATE product SET author = 'I.M. Pei', publisher = 'Taschen' WHERE product_code = 'SP1004';
-- UPDATE product SET author = 'Zaha Hadid', publisher = 'Phaidon' WHERE product_code = 'SP1005';
-- UPDATE product SET author = 'Norman Foster', publisher = 'Taschen' WHERE product_code = 'SP1006';
-- UPDATE product SET author = 'Richard Rogers', publisher = 'Phaidon' WHERE product_code = 'SP1007';
-- UPDATE product SET author = 'Rem Koolhaas', publisher = 'Monacelli' WHERE product_code = 'SP1008';
-- UPDATE product SET author = 'Tadao Ando', publisher = 'Taschen' WHERE product_code = 'SP1009';
-- UPDATE product SET author = 'Santiago Calatrava', publisher = 'Phaidon' WHERE product_code = 'SP1010';





