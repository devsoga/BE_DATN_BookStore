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
	('TK_USERDEMO', 'guest', '$2a$10$c3h6fvOYaAcAS3BDYZWRG.7NHD8WePgNugJKU5RYCgMM.46Ho7ewq', '0934567890', 'user@email.com', 'USER'),
    ('TK_USER01', 'devsoga', '$2a$10$c3h6fvOYaAcAS3BDYZWRG.7NHD8WePgNugJKU5RYCgMM.46Ho7ewq', '0934567890', 'dangkhoinguyen1501@email.com', 'USER'),
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
   ('KH_DEMO', 'Guest customer', 0, '', 'CUS_REGULAR', 'TK_USERDEMO'),
    ('KH_USER', 'Đặng Khôi Nguyên', 100, '123 Đường ABC, TP.HCM', 'CUS_SILVER', 'TK_USER01');

-- =========================
-- SUPPLIER
-- =========================
INSERT INTO supplier (supplier_code, supplier_name, address, phone_number, email) VALUES
('NCC01', 'NXB Tổng Hợp Hà Nội', '123 Đường Sách, Hà Nội', '0912345678', 'tonghop.hn@example.com'),
('NCC02', 'NXB Kim Đồng', '55 Quang Trung, Hà Nội', '0987654321', 'kimdong@example.com'),
('NCC03', 'Công Ty Fahasa', '40 Nguyễn Huệ, TP.HCM', '0901122334', 'fahasa@example.com'),
('NCC04', 'Công Ty Phân Phối Thiên Long', '27 Lê Duẩn, TP.HCM', '0911002200', 'thienlong@example.com'),
('NCC05', 'Nhà Sách Phương Nam', '212 Nguyễn Trãi, TP.HCM', '0903344556', 'phuongnam@example.com'),
('NCC06', 'Công Ty Văn Phòng Phẩm Hồng Hà', '15 Láng Hạ, Hà Nội', '0933221100', 'hongha@example.com'),
('NCC07', 'Công Ty ADC Book', '98 Điện Biên Phủ, Hà Nội', '0919988776', 'adcbook@example.com'),
('NCC08', 'Công Ty Đinh Tị Books', '24 Hoàng Cầu, Hà Nội', '0922233445', 'dinhti@example.com'),
('NCC09', 'Công Ty AZ Việt Nam', '19 Pasteur, TP.HCM', '0905566778', 'azvietnam@example.com'),
('NCC10', 'Công Ty Sách Alpha Books', '176 Thái Hà, Hà Nội', '0988112233', 'alphabooks@example.com');



-- =========================
-- PROMOTION TYPE & PROMOTION
-- =========================
-- Promotion types: percentage, fixed amount, free shipping
INSERT INTO promotion_type (promotion_type_code, promotion_type_name, description) VALUES
    ('PT_01', 'Percent','percentage'),
    ('PT_02', 'Amount','fixed amount');

-- Sample promotions
INSERT INTO promotion (promotion_code, promotion_name, description, value, promotion_type_code, status) VALUES
    ('VIP5', 'Silver Customer', 'Giáº£m 5% cho khÃ¡ch hÃ ng Silver', 0.05, 'PT_01', 1),
    ('VIP7', 'Gold Customer', 'Giáº£m 7% cho khÃ¡ch hÃ ng Gold', 0.07, 'PT_01', 1),
    ('VIP10', 'Diamond Customer', 'Giáº£m 10% cho khÃ¡ch hÃ ng Diamond', 0.10, 'PT_01', 1),
    ('FA200', 'Happy New Year', 'Giáº£m 200.000Ä‘', 200000, 'PT_02', 1),
    ('FA20', 'Noel', 'Giáº£m 20% cho Ä‘Æ¡n hÃ ng trong dá»‹p Noel', 0.20, 'PT_01', 1);

-- Set start_date and end_date for sample promotions so APIs return dates
UPDATE promotion
SET start_date = '2024-01-01 00:00:00',
	end_date = '2027-12-31 23:59:59'
WHERE promotion_code IN ('VIP5', 'VIP7', 'VIP10', 'FA200', 'FA20');

INSERT INTO coupon 
(
coupon_code, coupon_name, description, promotion_type_code, `value`, start_date, end_date
)
VALUES
('SALE50', 'Giảm 50%', 'Coupon giảm giá 50% đơn hàng', 'PT_01', 0.50,
 '2025-01-01 00:00:00', '2027-12-31 23:59:59'),

('G50K', 'Giảm 50k', 'Coupon giảm giá 50.000đ', 'PT_02', 50000,
 '2025-01-01 00:00:00', '2027-06-30 23:59:59');

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
	('LSP16', 'Pen','pen', 'Premium pens that deliver smooth writing and elegant design â€” ideal for professionals, collectors, and creatives.'),
	('LSP17', 'Draw','draw', 'A collection of acrylic paints, markers, and brushes that inspire artists to explore color, texture, and creativity.'),
	('LSP18', 'Studentbook','studentBook', 'Fun and colorful student notebooks featuring cute designs and high-quality paper for everyday school use.'),
	('LSP19', 'CompaEke','compaEke', 'Practical geometry tools including compasses, rulers, and setsquares â€” perfect for students and technical drawing tasks.'),
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
	 'A grumpy manâ€™s quiet holiday is ruined when his spirited neighbor drags him into a Christmas contest â€” and maybe, into love.',
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
	 'Mary Shelleyâ€™s gothic masterpiece about a scientist who creates life, only to be destroyed by his own creation and guilt.',
	 '/uploads/Horror/Horror3.jpg',
	 'Mary Shelley','Penguin Classics', TRUE, 'LSP02', NULL),
	
	('SP0204','Home Before Dark',
	 'A woman returns to her childhood home made famous by her fatherâ€™s horror memoir, only to discover the terrifying truth behind it.',
	 '/uploads/Horror/Horror4.jpg',
	 'Riley Sager','Dutton', TRUE, 'LSP02', NULL),
	
	('SP0205','Phantasma',
	 'In a deadly haunted mansion game, Ophelia must survive traps and demons to save her sister â€” without falling for the charming stranger guiding her.',
	 '/uploads/Horror/Horror5.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0206','Never Flinch',
	 'A gripping Stephen King thriller weaving revenge, obsession, and morality as two killersâ€™ paths collide in a deadly game of justice.',
	 '/uploads/Horror/Horror6.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0207','It',
	 'In the town of Derry, children face a terrifying entity that takes the form of their deepest fears â€” and returns decades later for revenge.',
	 '/uploads/Horror/Horror7.jpg',
	 'Stephen King','Viking Press', TRUE, 'LSP02', NULL),
	
	('SP0208','We Used to Live Here',
	 'A young coupleâ€™s dream home becomes a nightmare when strangers claiming to be its former residents refuse to leave. Reality soon unravels.',
	 '/uploads/Horror/Horror8.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0209','Roald Dahl Book of Ghost Stories',
	 'Fourteen eerie tales selected by Roald Dahl himself â€” classic ghost stories meant to unsettle, disturb, and chill you to the bone.',
	 '/uploads/Horror/Horror9.jpg',
	 'Roald Dahl (Editor)','Farrar, Straus and Giroux', TRUE, 'LSP02', NULL),
	
	('SP0210','The Haunting of Hill House',
	 'Four strangers explore a haunted mansion that preys on their fears. Shirley Jacksonâ€™s timeless tale of madness and the supernatural.',
	 '/uploads/Horror/Horror10.jpg',
	 'Shirley Jackson','Viking Press', TRUE, 'LSP02', NULL),
	
	-- SP0211 â†’ SP0220
	('SP0211','Hazelthorn',
	 'A chilling Southern horror set in 1950s Florida. A young boy sent to a reform school must survive cruelty and ghosts haunting its halls.',
	 '/uploads/Horror/Horror11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0212','Girl Dinner',
	 'A complex psychological horror about a mysterious house that defies space and logic, and the madness it brings to those who study it.',
	 '/uploads/Horror/Horror12.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0213','King Sorrow',
	 'Mary Shelleyâ€™s gothic masterpiece about a scientist who creates life, only to be destroyed by his own creation and guilt.',
	 '/uploads/Horror/Horror13.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0214','The Haunting of Paynes Hollow',
	 'A woman returns to her childhood home made famous by her fatherâ€™s horror memoir, only to discover the terrifying truth behind it.',
	 '/uploads/Horror/Horror14.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0215','Bog Queen',
	 'In a deadly haunted mansion game, Ophelia must survive traps and demons to save her sister â€” without falling for the charming stranger guiding her.',
	 '/uploads/Horror/Horror15.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0216','The Women of Wild Hill',
	 'A gripping Stephen King thriller weaving revenge, obsession, and morality as two killersâ€™ paths collide in a deadly game of justice.',
	 '/uploads/Horror/Horror16.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0217','The Graceview Patient',
	 'In the town of Derry, children face a terrifying entity that takes the form of their deepest fears â€” and returns decades later for revenge.',
	 '/uploads/Horror/Horror17.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0218','The Hong Kong Widow',
	 'A young coupleâ€™s dream home becomes a nightmare when strangers claiming to be its former residents refuse to leave. Reality soon unravels.',
	 '/uploads/Horror/Horror18.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0219','Crafting for Sinners',
	 'Fourteen eerie tales selected by Roald Dahl himself â€” classic ghost stories meant to unsettle, disturb, and chill you to the bone.',
	 '/uploads/Horror/Horror19.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0220','Toms Crossing',
	 'Four strangers explore a haunted mansion that preys on their fears. Shirley Jacksonâ€™s timeless tale of madness and the supernatural.',
	 '/uploads/Horror/Horror20.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	-- SP0221 â†’ SP0230
	('SP0221','Colin Gets Promoted and Dooms the World',
	 'A chilling Southern horror set in 1950s Florida. A young boy sent to a reform school must survive cruelty and ghosts haunting its halls.',
	 '/uploads/Horror/Horror21.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0222','The Last Witch',
	 'A complex psychological horror about a mysterious house that defies space and logic, and the madness it brings to those who study it.',
	 '/uploads/Horror/Horror22.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0223','The Salvage',
	 'Mary Shelleyâ€™s gothic masterpiece about a scientist who creates life, only to be destroyed by his own creation and guilt.',
	 '/uploads/Horror/Horror23.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0224','How to Fake a Haunting',
	 'A woman returns to her childhood home made famous by her fatherâ€™s horror memoir, only to discover the terrifying truth behind it.',
	 '/uploads/Horror/Horror24.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0225','If the Dead Belong Here',
	 'In a deadly haunted mansion game, Ophelia must survive traps and demons to save her sister â€” without falling for the charming stranger guiding her.',
	 '/uploads/Horror/Horror25.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0226','Atlas of Unknowable Things',
	 'A gripping Stephen King thriller weaving revenge, obsession, and morality as two killersâ€™ paths collide in a deadly game of justice.',
	 '/uploads/Horror/Horror26.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0227','Our Vicious Descent',
	 'In the town of Derry, children face a terrifying entity that takes the form of their deepest fears â€” and returns decades later for revenge.',
	 '/uploads/Horror/Horror27.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0228','Final Cut',
	 'A young coupleâ€™s dream home becomes a nightmare when strangers claiming to be its former residents refuse to leave. Reality soon unravels.',
	 '/uploads/Horror/Horror28.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0229','And the River Drags Her Down',
	 'Fourteen eerie tales selected by Roald Dahl himself â€” classic ghost stories meant to unsettle, disturb, and chill you to the bone.',
	 '/uploads/Horror/Horror29.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0230','The Midnight Knock',
	 'Four strangers explore a haunted mansion that preys on their fears. Shirley Jacksonâ€™s timeless tale of madness and the supernatural.',
	 '/uploads/Horror/Horror30.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	-- SP0231 â†’ SP0240
	('SP0231','The Night That Finds Us All',
	 'A chilling Southern horror set in 1950s Florida. A young boy sent to a reform school must survive cruelty and ghosts haunting its halls.',
	 '/uploads/Horror/Horror31.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0232','The Works of Vermin',
	 'A complex psychological horror about a mysterious house that defies space and logic, and the madness it brings to those who study it.',
	 '/uploads/Horror/Horror32.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0233','Ill Quit When Im Dead',
	 'Mary Shelleyâ€™s gothic masterpiece about a scientist who creates life, only to be destroyed by his own creation and guilt.',
	 '/uploads/Horror/Horror33.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0234','The Unveiling',
	 'A woman returns to her childhood home made famous by her fatherâ€™s horror memoir, only to discover the terrifying truth behind it.',
	 '/uploads/Horror/Horror34.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0235','Vampires at Sea',
	 'In a deadly haunted mansion game, Ophelia must survive traps and demons to save her sister â€” without falling for the charming stranger guiding her.',
	 '/uploads/Horror/Horror35.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0236','Slayers of Old',
	 'A gripping Stephen King thriller weaving revenge, obsession, and morality as two killersâ€™ paths collide in a deadly game of justice.',
	 '/uploads/Horror/Horror36.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0237','Herculine',
	 'In the town of Derry, children face a terrifying entity that takes the form of their deepest fears â€” and returns decades later for revenge.',
	 '/uploads/Horror/Horror37.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0238','The Resurrectionist',
	 'A young coupleâ€™s dream home becomes a nightmare when strangers claiming to be its former residents refuse to leave. Reality soon unravels.',
	 '/uploads/Horror/Horror38.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0239','Psychopomp & Circumstance',
	 'Fourteen eerie tales selected by Roald Dahl himself â€” classic ghost stories meant to unsettle, disturb, and chill you to the bone.',
	 '/uploads/Horror/Horror39.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),
	
	('SP0240','Uncanny Valley Girls: Essays on Horror, Survival, and Love',
	 'Four strangers explore a haunted mansion that preys on their fears. Shirley Jacksonâ€™s timeless tale of madness and the supernatural.',
	 '/uploads/Horror/Horror40.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP02', NULL),


-- LSP03 Fantasy
	('SP0301','Dungeon Crawler Carl',
	 'A hilarious and inventive LitRPG adventure following Carl as he battles through a deadly dungeon after the apocalypse. Packed with humor, dark twists, and nonstop action.',
	 '/uploads/Fantasy/Fantasy1.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0302','The Hobbit: Illustrated Edition',
	 'J.R.R. Tolkienâ€™s timeless tale of Bilbo Baggins and his unexpected journey through Middle-earth. A beautifully illustrated modern classic of adventure and courage.',
	 '/uploads/Fantasy/Fantasy2.jpg',
	 'J.R.R. Tolkien','HarperCollins', TRUE, 'LSP03', NULL),
	
	('SP0303','The Alchemist',
	 'Paulo Coelhoâ€™s modern classic about following your dreams and listening to your heart. A parable of self-discovery that has inspired millions worldwide.',
	 '/uploads/Fantasy/Fantasy3.jpg',
	 'Paulo Coelho','HarperOne', TRUE, 'LSP03', NULL),
	
	('SP0304','A Court of Thorns and Roses',
	 'A captivating romantic fantasy about Feyre, a huntress who becomes entangled in a dangerous faerie world after killing a wolf. Passion, magic, and peril intertwine in this spellbinding tale.',
	 '/uploads/Fantasy/Fantasy4.jpg',
	 'Sarah J. Maas','Bloomsbury', TRUE, 'LSP03', NULL),
	
	('SP0305','Throne of Glass',
	 'The beginning of Sarah J. Maasâ€™s epic fantasy series. Celaena, a deadly assassin, is offered freedom if she can win a brutal competition to become the Kingâ€™s Champion.',
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
	 'Aelinâ€™s path to the throne becomes more perilous than ever as kingdoms fall into chaos. A gripping continuation of the Throne of Glass saga filled with war, magic, and destiny.',
	 '/uploads/Fantasy/Fantasy10.jpg',
	 'Sarah J. Maas','Bloomsbury', TRUE, 'LSP03', NULL),
	
	('SP0311','Alchemy of Secrets',
	 'A hilarious and inventive LitRPG adventure following Carl as he battles through a deadly dungeon after the apocalypse. Packed with humor, dark twists, and nonstop action.',
	 '/uploads/Fantasy/Fantasy11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0312','Bonds of Hercules',
	 'J.R.R. Tolkienâ€™s timeless tale of Bilbo Baggins and his unexpected journey through Middle-earth. A beautifully illustrated modern classic of adventure and courage.',
	 '/uploads/Fantasy/Fantasy12.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0313','The Wrath of the Fallen',
	 'Paulo Coelhoâ€™s modern classic about following your dreams and listening to your heart. A parable of self-discovery that has inspired millions worldwide.',
	 '/uploads/Fantasy/Fantasy13.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0314','The Everlasting',
	 'A captivating romantic fantasy about Feyre, a huntress who becomes entangled in a dangerous faerie world after killing a wolf. Passion, magic, and peril intertwine in this spellbinding tale.',
	 '/uploads/Fantasy/Fantasy14.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0315','Hazelthorn',
	 'The beginning of Sarah J. Maasâ€™s epic fantasy series. Celaena, a deadly assassin, is offered freedom if she can win a brutal competition to become the Kingâ€™s Champion.',
	 '/uploads/Fantasy/Fantasy15.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0316','The Ascended',
	 'Nesta Archeron must face her past and her fiery bond with Cassian while the Night Court faces new threats. A passionate and empowering fantasy of love, trauma, and redemption.',
	 '/uploads/Fantasy/Fantasy16.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP03', NULL),
	
	('SP0317','The Things Gods Break',
	 'Aelin Galathynius returns to her empire seeking vengeance and freedom. The fourth book in the Throne of Glass series delivers powerful action and emotional depth.',
	 '/uploads/Fantasy/Fantasy17.jpg',
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
	 'Aelinâ€™s path to the throne becomes more perilous than ever as kingdoms fall into chaos. A gripping continuation of the Throne of Glass saga filled with war, magic, and destiny.',
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
	 'A fresh playbook for leading beyond the rulebookâ€”challenge assumptions, empower teams, and create accountable, adaptive cultures.',
	 '/uploads/Business/Business11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0412','Unconventional Business',
	 'Practical strategies for building bold, resilient companiesâ€”lean experiments, customer obsession, and differentiated positioning.',
	 '/uploads/Business/Business12.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0413','Integrity Moments',
	 'Short, thoughtful reflections on ethics at workâ€”honesty, accountability, and trust as daily leadership practices.',
	 '/uploads/Business/Business13.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0414','10 Biblical Principles for Business',
	 'Faith-based guidance for entrepreneurship and leadership: stewardship, service, integrity, diligence, and wise decision-making.',
	 '/uploads/Business/Business14.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0415','Work that Matters',
	 'A purpose-driven approach to career designâ€”clarify values, focus on impact, and align strengths to meaningful goals.',
	 '/uploads/Business/Business15.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0416','94X',
	 'A growth framework for scaling resultsâ€”set clear metrics, systematize operations, and compound small wins into outsized outcomes.',
	 '/uploads/Business/Business16.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP04', NULL),
	
	('SP0417','Bet on Talent',
	 'How to build a standout culture by hiring for character, developing people, and keeping top performers engaged.',
	 '/uploads/Business/Business17.jpg',
	 'Dee Ann Turner','Baker Books', TRUE, 'LSP04', NULL),
	
	('SP0418','Every Good Endeavor',
	 'A perspective on integrating faith and workâ€”vocation, calling, and contributing to the common good with excellence.',
	 '/uploads/Business/Business18.jpg',
	 'Timothy Keller; Katherine Leary Alsdorf','Dutton', TRUE, 'LSP04', NULL),
	
	('SP0419','Excellence Wins',
	 'Lessons in world-class service and leadershipâ€”set high standards, empower teams, and deliver consistency at scale.',
	 '/uploads/Business/Business19.jpg',
	 'Horst Schulze; Dean Merrill','Zondervan', TRUE, 'LSP04', NULL),
	
	('SP0420','FIRE in the Workplace',
	 'A practical guide to preventing and managing workplace crisesâ€”policies, training, and culture that reduce risk and burnout.',
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
	('SP0601','Man''s Search for Meaning',
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
	 'Created by Americas Test Kitchen, this award-winning cookbook helps kids master over 100 delicious recipesâ€”from breakfast to dinnerâ€”with full-color photos and step-by-step guides.',
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
	 'Samin Nosrat explains the four key elements of cookingâ€”salt, fat, acid, and heatâ€”through science, storytelling, and recipes that make you a confident home cook.',
	 '/uploads/Cook/Cook6.jpg',
	 'Samin Nosrat','Simon & Schuster', TRUE, 'LSP07', NULL),
	
	('SP0707','The Complete Anti-Inflammatory Diet for Beginners: A No-Stress Meal Plan with Easy Recipes to Heal the Immune System',
	 'A practical beginnerâ€™s guide featuring meal plans and simple recipes designed to reduce inflammation and boost overall health naturally.',
	 '/uploads/Cook/Cook7.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0708','Half Baked Harvest Super Simple: More Than 125 Recipes for Instant, Overnight, Meal-Prepped, and Easy Comfort Foods: A Cookbook',
	 '125 effortless comfort food recipes for busy people. Includes make-ahead, slow cooker, and 30-minute dishes that taste homemade and hearty.',
	 '/uploads/Cook/Cook8.jpg',
	 'Tieghan Gerard','Clarkson Potter', TRUE, 'LSP07', NULL),
	
	('SP0709','The King Arthur Baking Company Big Book of Bread: 125+ Recipes for Every Baker (a Cookbook)',
	 'A complete guide to bread baking with over 125 tried-and-true recipesâ€”from rustic loaves to soft sandwich breadsâ€”by Americaâ€™s most trusted baking experts.',
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
	 'Created by Americas Test Kitchen, this award-winning cookbook helps kids master over 100 delicious recipesâ€”from breakfast to dinnerâ€”with full-color photos and step-by-step guides.',
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
	
	('SP0716','PeÃ±in Guide Spanish Wine 2025 (Spanish Wines)',
	 'Samin Nosrat explains the four key elements of cookingâ€”salt, fat, acid, and heatâ€”through science, storytelling, and recipes that make you a confident home cook.',
	 '/uploads/Cook/Cook16.jpg',
	 'PeÃ±Ã­n Guide','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0717','Dac Biet: An Extra-Special Vietnamese Cookbook',
	 'A practical beginnerâ€™s guide featuring meal plans and simple recipes designed to reduce inflammation and boost overall health naturally.',
	 '/uploads/Cook/Cook17.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0718','Quick and Easy Vietnamese: Everyday Vietnamese Recipes for the Home Cook',
	 '125 effortless comfort food recipes for busy people. Includes make-ahead, slow cooker, and 30-minute dishes that taste homemade and hearty.',
	 '/uploads/Cook/Cook18.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0719','Italian Coastal: Recipes and Stories from Where the Land Meets the Sea',
	 'A complete guide to bread baking with over 125 tried-and-true recipesâ€”from rustic loaves to soft sandwich breadsâ€”by Americaâ€™s most trusted baking experts.',
	 '/uploads/Cook/Cook19.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),
	
	('SP0720','Recipes from my Vietnamese Kitchen',
	 'A collection of slow cooker recipes that promote healing and reduce inflammation. Features nutritious meals that are easy to prepare and full of flavor.',
	 '/uploads/Cook/Cook20.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP07', NULL),



-- LSP08 Poetry
	('SP0801','Beowulf: A New Verse Translation',
	 'A timeless epic of heroism and fate. Seamus Heaneyâ€™s award-winning translation brings new life to this ancient tale of courage and mortality.',
	 '/uploads/Poetry/Poetry1.jpg',
	 'Seamus Heaney (Translator)','W. W. Norton & Company', TRUE, 'LSP08', NULL),
	
	('SP0802','Where the Sidewalk Ends: Poems and Drawings',
	 'A beloved collection of humorous and heartfelt poems paired with whimsical drawings from the creator of The Giving Tree.',
	 '/uploads/Poetry/Poetry2.jpg',
	 'Shel Silverstein','HarperCollins', TRUE, 'LSP08', NULL),
	
	('SP0803','Save Me An Orange',
	 'A tender debut exploring pain, love, and self-acceptance. Hayleyâ€™s poetry captures fleeting joy and the beauty of simply sharing an orange.',
	 '/uploads/Poetry/Poetry3.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),
	
	('SP0804','Lord of the Butterflies',
	 'Andrea Gibsonâ€™s award-winning poetry on gender, love, and loss â€” powerful, emotional, and crafted with lyrical precision.',
	 '/uploads/Poetry/Poetry4.jpg',
	 'Andrea Gibson','Button Poetry', TRUE, 'LSP08', NULL),
	
	('SP0805','The Tears That Taught Me',
	 'A moving journey through heartbreak, healing, and rebirth. Each poem invites readers to embrace emotion and bloom with purpose.',
	 '/uploads/Poetry/Poetry5.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),
	
	('SP0806','Devotions: The Selected Poems of Mary Oliver',
	 'A landmark collection spanning five decades of Mary Oliverâ€™s Pulitzer-winning poetry â€” celebrating nature, love, and the human spirit.',
	 '/uploads/Poetry/Poetry6.jpg',
	 'Mary Oliver','Penguin Press', TRUE, 'LSP08', NULL),
	
	('SP0807','The Prophet (Reader''s Library Classics)',
	 'Kahlil Gibranâ€™s spiritual masterpiece exploring love, life, and purpose. A timeless collection of poetic wisdom and soulful reflection.',
	 '/uploads/Poetry/Poetry7.jpg',
	 'Kahlil Gibran','Various Publishers', TRUE, 'LSP08', NULL),
	
	('SP0808','A Light in the Attic (Special Edition)',
	 'Shel Silversteinâ€™s classic poetry collection returns with new poems â€” full of wit, wonder, and his signature illustrations.',
	 '/uploads/Poetry/Poetry8.jpg',
	 'Shel Silverstein','HarperCollins', TRUE, 'LSP08', NULL),
	
	('SP0809','Heart Talk: Poetic Wisdom for a Better Life',
	 'Cleo Wade blends poetry and positivity in this uplifting collection of daily affirmations and modern wisdom for the heart and soul.',
	 '/uploads/Poetry/Poetry9.jpg',
	 'Cleo Wade','Atria Books', TRUE, 'LSP08', NULL),
	
	('SP0810','I Hope You Remember: Poems on Loving, Longing, and Living',
	 'Josie Balkaâ€™s debut poetry collection on love, nostalgia, and growth â€” honest, relatable, and deeply emotional.',
	 '/uploads/Poetry/Poetry10.jpg',
	 'Josie Balka','Various Publishers', TRUE, 'LSP08', NULL),

	('SP0811','Tender Lines: Modern Poems',
	 'A contemporary collection exploring intimacy, memory, and quiet resilience through spare, tender lines.',
	 '/uploads/Poetry/Poetry11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),

	('SP0812','Night Songs',
	 'An evocative sequence of poems about cities at night, longing, and unexpected encounters.',
	 '/uploads/Poetry/Poetry12.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),

	('SP0813','Salt & Light',
	 'A lyrical collection opening the small moments of daily life into larger revelations.',
	 '/uploads/Poetry/Poetry13.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),

	('SP0814','Paper Birds',
	 'Imagistic poems that fold memory and myth into delicate, surprising shapes.',
	 '/uploads/Poetry/Poetry14.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),

	('SP0815','Maps of Longing',
	 'A collection mapping desire and distance through concise, haunting language.',
	 '/uploads/Poetry/Poetry15.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),

	('SP0816','Blue Hour',
	 'Meditative poems on twilight, transition, and the quiet acts that shape our days.',
	 '/uploads/Poetry/Poetry16.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),

	('SP0817','Small Fires',
	 'Short, fiery poems that burn with clarityâ€”about love, loss, and getting through.',
	 '/uploads/Poetry/Poetry17.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),

	('SP0818','The Quiet Earth',
	 'A tender examination of place and belonging in the contemporary world.',
	 '/uploads/Poetry/Poetry18.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),

	('SP0819','After the Storm',
	 'Poems about survival, repair, and learning to live with the traces left behind.',
	 '/uploads/Poetry/Poetry19.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),

	('SP0820','Morning Letters',
	 'A series of letter-poems that move between private confession and public address.',
	 '/uploads/Poetry/Poetry20.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP08', NULL),


	

-- LSP09 Art
	('SP0901','The Art Thief: A True Story of Love, Crime, and a Dangerous Obsession',
	 'The true story of StÃ©phane Breitwieser â€” the worldâ€™s most successful art thief. For years he stole masterpieces from museums across Europe, keeping them not for profit, but for love.',
	 '/uploads/Art/Art1.jpg',
	 'Michael Finkel','Knopf', TRUE, 'LSP09', NULL),
	
	('SP0902','The Reverse Coloring Book: The Book Has the Colors, You Draw the Lines!',
	 'A creative twist on coloring â€” the colors are already there, and you draw the lines. Let your imagination flow freely across watercolor pages filled with shapes and inspiration.',
	 '/uploads/Art/Art2.jpg',
	 'Kendra Norton','Workman Publishing', TRUE, 'LSP09', NULL),
	
	('SP0903','How To Draw Everything: 600 Simple Step By Step Drawings For Kids Ages 4 to 8',
	 'An easy, fun drawing book with over 600 guided images for kids. Boosts creativity, pen control, and artistic confidence with themes from animals to holidays.',
	 '/uploads/Art/Art3.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP09', NULL),
	
	('SP0904','The Art of War',
	 'Sun Tzuâ€™s timeless guide to strategy, leadership, and wisdom. Lessons from ancient warfare that still shape modern business, politics, and personal success.',
	 '/uploads/Art/Art4.jpg',
	 'Sun Tzu','Various Publishers', TRUE, 'LSP09', NULL),
	
	('SP0905','Perspective Made Easy',
	 'A beginner-friendly guide to mastering perspective in art. With 250+ illustrations, this classic helps you draw depth, distance, and dimension with confidence.',
	 '/uploads/Art/Art5.jpg',
	 'Ernest R. Norling','Dover Publications', TRUE, 'LSP09', NULL),
	
	('SP0906','All the Beauty in the World: The Metropolitan Museum of Art and Me',
	 'A museum guardâ€™s moving memoir inside the halls of the Met. A deeply personal journey through art, humanity, and the quiet power of beauty.',
	 '/uploads/Art/Art6.jpg',
	 'Patrick Bringley','Avid Reader Press', TRUE, 'LSP09', NULL),
	
	('SP0907','The Complete Illustrated Kama Sutra',
	 'An artistic and cultural celebration of the ancient Kama Sutra, featuring 260 exquisite artworks from museums worldwide. A timeless tribute to beauty and connection.',
	 '/uploads/Art/Art7.jpg',
	 'Lance Dane','Inner Traditions', TRUE, 'LSP09', NULL),
	
	('SP0908','Morpho: Simplified Forms â€“ Anatomy for Artists',
	 'Michel Lauricella simplifies human anatomy into basic shapes, helping artists understand structure and proportion. A must-have for improving figure drawing skills.',
	 '/uploads/Art/Art8.jpg',
	 'Michel Lauricella','Rocky Nook', TRUE, 'LSP09', NULL),
	
	('SP0909','Paint by Sticker: Works of Art',
	 'Recreate twelve famous paintings sticker by sticker â€” from Van Gogh to Renoir. Relaxing, creative, and frame-worthy when finished.',
	 '/uploads/Art/Art9.jpg',
	 'Workman Publishing','Workman Publishing', TRUE, 'LSP09', NULL),
	
	('SP0910','The Artistâ€™s Way Workbook',
	 'A practical workbook for unlocking creativity and self-expression. Filled with exercises and reflections to help you rediscover your artistic potential.',
	 '/uploads/Art/Art10.jpg',
	 'Julia Cameron','TarcherPerigee', TRUE, 'LSP09', NULL),
	
	('SP0911','Pattern Cutting Deconstructed: Wearable Art',
	 'An exploration of experimental pattern cutting and sculptural garmentsâ€”step-by-step methods, draping ideas, and case studies for wearable art.',
	 '/uploads/Art/Art11.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP09', NULL),
	
	('SP0912','Fashion in the Metaverse: Designs and Technologies Shaping the Future',
	 'A forward-looking guide to digital fashion, virtual runways, NFTs, avatars, and the tools redefining design in immersive worlds.',
	 '/uploads/Art/Art12.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP09', NULL),
	
	('SP0913','Aino + Alvar Aalto: A Life Together',
	 'An intimate portrait of Aino and Alvar Aaltoâ€™s partnership, featuring modernist architecture, furniture, and correspondence across their careers.',
	 '/uploads/Art/Art13.jpg',
	 'Various Authors','Phaidon', TRUE, 'LSP09', NULL),
	
	('SP0914','S.O.A.P class: Create your own set of prompt cards',
	 'A hands-on workbook to design personal creative prompt cardsâ€”frameworks, templates, and exercises to spark ideas on demand.',
	 '/uploads/Art/Art14.jpg',
	 'Various Authors','Various Publishers', TRUE, 'LSP09', NULL),
	
	('SP0915','Sybil and David Yurman: Artists and Jewelers',
	 'A visual history of the Yurmansâ€™ studio practiceâ€”sculptural jewelry, brand evolution, and signature cable motifs in rich photography.',
	 '/uploads/Art/Art15.jpg',
	 'Various Authors','Rizzoli', TRUE, 'LSP09', NULL),
	
	('SP0916','Japan Style',
	 'A curated survey of Japanese aestheticsâ€”from wabi-sabi interiors to craft, textiles, and garden designâ€”blending tradition with minimalism.',
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
	 'Jane Jacobsâ€™s classic critique of modern urban planning. She argues that true city vitality comes from diversity, street life, and human scale rather than rigid design and planning theories.',
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
	 'A practical handbook that breaks down interior design principlesâ€”covering color, balance, lighting, and proportionâ€”to help you create stylish and functional spaces.',
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
	 'Jane Jacobsâ€™s classic critique of modern urban planning. She argues that true city vitality comes from diversity, street life, and human scale rather than rigid design and planning theories.',
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
	 'A practical handbook that breaks down interior design principlesâ€”covering color, balance, lighting, and proportionâ€”to help you create stylish and functional spaces.',
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
	
	('SP1103','Bandai MG 1/100 Turn A GUNDAM (âˆ€ Gundam) Assembly Model',
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
	('SP1201','Genuine Bandai Dragon Ball â€“ Yamcha & Puar History Box Figure',
	 'Yamcha cÃ¹ng Puar trong dÃ²ng History Box, táº¡o dÃ¡ng Ä‘á»™ng vÃ  chi tiáº¿t sáº¯c nÃ©t; sÆ¡n Ä‘á»• bÃ³ng Ä‘áº¹p vÃ  Ä‘á»™ hoÃ n thiá»‡n cao cho fan Dragon Ball.',
	 '/uploads/Figure/Figure1.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1202','Dragon Ball â€“ Gogeta Super Saiyan Grandista Figure genuine Bandai',
	 'Gogeta dáº¡ng Super Saiyan thuá»™c dÃ²ng Grandista vá»›i tá»‰ lá»‡ lá»›n, cÆ¡ báº¯p kháº¯c há»a rÃµ rÃ ng vÃ  náº¿p Ã¡o mÆ°á»£t, thÃ­ch há»£p trÆ°ng bÃ y ná»•i báº­t.',
	 '/uploads/Figure/Figure2.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1203','Dragon Ball â€“ Majinbuu Ichiban Kuji Last One Figure (Fat Buu) genuine Bandai',
	 'PhiÃªn báº£n Last One cá»§a Majin Buu (Fat Buu) trong Ichiban Kuji: kÃ­ch thÆ°á»›c áº¥n tÆ°á»£ng, khuÃ´n máº·t biá»ƒu cáº£m vÃ  lá»›p sÆ¡n bÃ³ng báº©y.',
	 '/uploads/Figure/Figure3.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1204','Genuine Bandai Yatogami Tohka Glitter & Glamours â€“ Date A Live V Figure',
	 'Tohka Yatogami tá»« Date A Live trong dÃ²ng Glitter & Glamours: táº¡o dÃ¡ng duyÃªn dÃ¡ng, náº¿p vÃ¡y chi tiáº¿t vÃ  sáº¯c tÃ­m Ä‘áº·c trÆ°ng.',
	 '/uploads/Figure/Figure4.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1205','Dragon Ball â€“ Vegeta Blue Blood Of Saiyans Figure genuine Bandai',
	 'Vegeta dáº¡ng Super Saiyan Blue thuá»™c series Blood of Saiyans, tÆ° tháº¿ máº¡nh máº½; hiá»‡u á»©ng shading vÃ  váº¿t xÆ°á»›c tÄƒng Ä‘á»™ chÃ¢n thá»±c.',
	 '/uploads/Figure/Figure5.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1206','Dragon Ball â€“ Songoku Super Saiyan 3 1/6 Scale Resin Model DK Studio',
	 'MÃ´ hÃ¬nh resin tá»‰ lá»‡ 1/6 cá»§a Son Goku Super Saiyan 3 tá»« DK Studio: mÃ¡i tÃ³c dÃ i hoÃ nh trÃ¡ng, dÃ¡ng Ä‘á»©ng vá»¯ng vÃ  chi tiáº¿t cÆ¡ báº¯p ná»•i khá»‘i.',
	 '/uploads/Figure/Figure6.jpg',
	 'DK Studio','DK Studio', TRUE, 'LSP12', NULL),
	
	('SP1207','One Piece â€“ Doflamingo Ichiban Kuji Masterlise Figure genuine Bandai',
	 'Doflamingo phiÃªn báº£n Masterlise vá»›i Ã¡o lÃ´ng biá»ƒu tÆ°á»£ng, táº¡o dÃ¡ng tá»± tin; chi tiáº¿t gÃ¢n cÆ¡ vÃ  Ä‘Æ°á»ng gáº¥p váº£i Ä‘Æ°á»£c xá»­ lÃ½ ká»¹.',
	 '/uploads/Figure/Figure7.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1208','One Piece â€“ Zoro Dressrosa Battle Ichiban Kuji Figure genuine Banpresto',
	 'Zoro tráº¡ng thÃ¡i chiáº¿n Ä‘áº¥u táº¡i Dressrosa: tÆ° tháº¿ vung kiáº¿m dá»©t khoÃ¡t, cÆ¡ báº¯p ráº¯n cháº¯c vÃ  nÆ°á»›c sÆ¡n má»‹n, bá»n mÃ u.',
	 '/uploads/Figure/Figure8.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1209','One Piece â€“ Bonney Girls Collection Vol.2 Ichiban Kuji Figure genuine Banpresto',
	 'Jewelry Bonney trong dÃ²ng Girls Collection Vol.2: táº¡o dÃ¡ng gá»£i cáº£m, mÃ u sáº¯c tÆ°Æ¡i vÃ  cÃ¡c chi tiáº¿t phá»¥ kiá»‡n tinh xáº£o.',
	 '/uploads/Figure/Figure9.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1210','One Piece â€“ Boa Hancock Glitter & Glamours Figure genuine Bandai',
	 'Boa Hancock dÃ²ng Glitter & Glamours vá»›i tá»· lá»‡ chuáº©n, náº¿p vÃ¡y vÃ  tÃ³c Ä‘á»• mÆ°á»£t; phÃ¹ há»£p trÆ°ng bÃ y cÃ¹ng bá»™ sÆ°u táº­p One Piece.',
	 '/uploads/Figure/Figure10.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),
	
	('SP1211','Premium One Piece â€“ Sabo Premium Figure The Metallic Genuine Bandai',
	 'Sabo phiÃªn báº£n Metallic hoÃ n thiá»‡n lá»›p sÆ¡n Ã¡nh kim áº¥n tÆ°á»£ng; táº¡o dÃ¡ng máº¡nh máº½, phÃ¹ há»£p lÃ m Ä‘iá»ƒm nháº¥n tá»§ trÆ°ng bÃ y.',
	 '/uploads/Figure/Figure11.jpg',
	 'Banpresto','BANDAI SPIRITS', TRUE, 'LSP12', NULL),


-- LSP13 Calculator
	('SP1301','Casio fx-880BTG-BK',
	 'Premium scientific calculator in elegant black with Bluetooth connectivity, Natural Textbook Display, and fast calculations â€” ideal for students and professionals.',
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
	 'A dreamy pastel notebook that sparks imagination and positivity. Compact and stylishâ€”your perfect creative companion.',
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
	 'Large easy-to-read digital display with 10-year battery life, 100 m water resistance, LED light, stopwatch, timer, and daily alarms â€” black resin band/bezel.',
	 '/uploads/Watch/Watch1.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	('SP1502','CASIO AE-1500WHC-8AV',
	 'Same AE-1500 features â€” oversized screen, 10-year battery, 100 m water resistance, LED light, stopwatch/timer/alarms â€” in the gray/8AV colorway.',
	 '/uploads/Watch/Watch2.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	-- AE-1700H: world time, 10 nÄƒm pin
	('SP1503','CASIO AE-1700H-1BV',
	 'World Time digital with 10-year battery, 100 m water resistance, LED light, 5 alarms, stopwatch, countdown timer, and world map display â€” 1BV black variant.',
	 '/uploads/Watch/Watch3.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	('SP1504','CASIO AE-1700H-1AV',
	 'AE-1700 series: World Time, 10-year battery, 100 m water resistance, LED light, 5 alarms, stopwatch and timer â€” classic 1AV black finish.',
	 '/uploads/Watch/Watch4.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	('SP1505','CASIO AE-1700H-1A2V',
	 'AE-1700 World Time with blue accent (1A2V): 10-year battery, 100 m water resistance, LED light, 5 alarms, stopwatch, and countdown timer.',
	 '/uploads/Watch/Watch5.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	-- AQ-230EM: retro ana-digi, dÃ¢y thÃ©p
	('SP1506','CASIO AQ-230EM-7A',
	 'Retro ana-digi with minimalist silver dial, stainless-steel mesh band, dual time, daily alarm, stopwatch, auto calendar, and 30 m water resistance.',
	 '/uploads/Watch/Watch6.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	('SP1507','CASIO AQ-230EM-2A',
	 'Classic rectangular ana-digi in navy/blue tone with stainless-steel mesh band, dual time, alarm, stopwatch, auto calendar, and 30 m water resistance.',
	 '/uploads/Watch/Watch7.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	-- A158WEM: vintage digital, dÃ¢y lÆ°á»›i thÃ©p
	('SP1508','CASIO A158WEM-7',
	 'Iconic vintage digital with stainless-steel mesh band, LED light, stopwatch, daily alarm, auto calendar, and water resistance â€” silver dial (-7).',
	 '/uploads/Watch/Watch8.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	('SP1509','CASIO A158WEM-3',
	 'Classic A158 with mesh band and green-tone dial (-3): LED light, stopwatch, daily alarm, auto calendar, and water resistance in a slim retro case.',
	 '/uploads/Watch/Watch9.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	-- MWD-100HD: digital 100m WR, dÃ¢y thÃ©p
	('SP1510','CASIO MWD-100HD-1BV',
	 'Rugged digital with stainless-steel band, 100 m water resistance, oversized LCD, LED light, 5 alarms, stopwatch, countdown timer, and full auto calendar.',
	 '/uploads/Watch/Watch10.jpg',
	 'Casio','Casio', TRUE, 'LSP15', NULL),
	
	-- AQ-240E: má»ng, chá»¯ nháº­t, ana-digi
	('SP1511','CASIO AQ-240E-3A',
	 'Slim rectangular ana-digi with green dial accents, dual time, daily alarm, hourly signal, stopwatch, and 30 m water resistance â€” elegant retro styling.',
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
	
	('SP1606','Allan Dâ€™lious 0502RR Rollerball Pen',
	 'A stylish Allan Dâ€™lious rollerball pen featuring a balanced body and smooth ink deliveryâ€”perfect for business professionals and daily note-taking.',
	 '/uploads/Pen/Pen6.jpg',
	 'Allan Dâ€™lious','Allan Dâ€™lious', TRUE, 'LSP16', NULL),
	
	('SP1607','Lamy Al-Star Pacific Special Edition 2017 Fountain Pen',
	 'A limited-edition fountain pen in a vibrant Pacific blue aluminum finish. Lightweight, durable, and designed for smooth, expressive writing.',
	 '/uploads/Pen/Pen7.jpg',
	 'Lamy','Lamy', TRUE, 'LSP16', NULL),
	
	('SP1608','Allan Dâ€™lious AG0302BBK Gold-Plated Ballpoint Pen',
	 'An elegant gold-plated ballpoint pen with premium finish and durable twist mechanism. Combines luxury and everyday practicality.',
	 '/uploads/Pen/Pen8.jpg',
	 'Allan Dâ€™lious','Allan Dâ€™lious', TRUE, 'LSP16', NULL),
	
	('SP1609','Montblanc MeisterstÃ¼ck UNICEF Solitaire LeGrand Rollerball MB116084',
	 'A luxurious Montblanc MeisterstÃ¼ck LeGrand rollerball pen created in support of UNICEF. Crafted with exquisite detailing and timeless sophistication.',
	 '/uploads/Pen/Pen9.jpg',
	 'Montblanc','Montblanc', TRUE, 'LSP16', NULL),
	
	('SP1610','Parker IM Monochrome Blue Rollerball Pen 2172965',
	 'A sleek all-blue rollerball pen from the Parker IM Monochrome series. Minimalist design meets smooth performance for modern professionals.',
	 '/uploads/Pen/Pen10.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1611','Parker JOT SS GT FP M BLU GB 2030948 Fountain Pen',
	 'Parker Jotter Stainless Steel Gold Trim fountain pen, medium nib. Smooth ink flow, reliable snap cap, and gift boxâ€”everyday elegance.',
	 '/uploads/Pen/Pen11.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1612','Parker Jotter Ballpoint Pen D-WTL Blue CT TB6-1953411',
	 'Iconic Parker Jotter ballpoint in Blue with chrome trim. Click action, durable stainless barrel, and smooth Quinkflow refill.',
	 '/uploads/Pen/Pen12.jpg',
	 'Parker','Parker', TRUE, 'LSP16', NULL),
	
	('SP1613','Parker IM SE GB4 Ballpoint Pen â€“ 2074161',
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
	
	('SP1705','Thien Long Colokit ART MARKER AM-C001 â€“ 24-color markers',
	 'Set of 24 vivid markers that dry quickly and resist smudging. Suited for illustration, posters, and decorative note-taking.',
	 '/uploads/Draw/Draw5.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1706','Colokit Doraemon TCR-C04/DO â€“ 12-color twisting wax pen',
	 '12 twistable wax crayonsâ€”no sharpening needed. Smooth strokes, low mess, great for kidsâ€™ coloring and school projects.',
	 '/uploads/Draw/Draw6.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1707','Thien Long Colokit TCR-C007/DO â€“ 12-color twisted wax pen',
	 '12 twist wax pens with sturdy barrels and even color laydown. Works well on drawing paper, notebooks, and craft items.',
	 '/uploads/Draw/Draw7.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1708','Colokit Doraemon TCR-C05/DO â€“ 18-color twisting wax pen',
	 '18 rich twistable crayons offering wide color variety for bold, playful artwork and classroom activities.',
	 '/uploads/Draw/Draw8.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1709','DIY Wooden Painting Set â€“ Thien Long Transport Colokit KIT-C032',
	 'DIY wooden painting kit themed around vehicles. Includes pre-cut wooden pieces and paintsâ€”fun for learning and creativity.',
	 '/uploads/Draw/Draw9.jpg',
	 'Colokit','Thien Long Group', TRUE, 'LSP17', NULL),
	
	('SP1710','Thien Long Colokit TCR-C008/DO â€“ 18-color twisted wax pen',
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
	
	('SP1804','Sao Mai â€œKeep Calmâ€ Lined Notebook',
	 'A motivational â€œKeep Calmâ€ notebook from Sao Mai with soft pages and a modern designâ€”ideal for school or personal planning.',
	 '/uploads/Studentbook/Studentbook4.jpg',
	 'Sao Mai','Sao Mai', TRUE, 'LSP18', NULL),
	
	('SP1805','Sao Mai â€œEmotionâ€ Lined Notebook',
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
	
	('SP1808','Pupil â€œNew Waveâ€ Lined Notebook',
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
	 'Durable, precise school compass with smooth adjustment wheel and firm needle gripâ€”great for accurate circles in class or technical drawing.',
	 '/uploads/CompaEke/CompaEke1.jpg',
	 'Hong Ha','Hong Ha', TRUE, 'LSP19', NULL),
	
	('SP1902','Compa Thien Long C-015',
	 'Modern compass with built-in mechanical pencil and fine hinge controlâ€”ideal for neat, precise geometry work.',
	 '/uploads/CompaEke/CompaEke2.jpg',
	 'Thien Long','Thien Long', TRUE, 'LSP19', NULL),
	
	('SP1903','Compa Thien Long C-017',
	 'All-metal compass with micro-adjust knob and anti-slip needle. Includes spare lead for consistent, exact arcs.',
	 '/uploads/CompaEke/CompaEke3.jpg',
	 'Thien Long','Thien Long', TRUE, 'LSP19', NULL),
	
	('SP1904','Eke Set â€“ 4 Pieces',
	 'Complete geometry kit: 30Â°/60Â° and 45Â° set squares, 20 cm ruler, and 180Â° protractor. Clear acrylic with easy-to-read mm scale.',
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
	 'Pastel compass set in a compact caseâ€”includes spare leads and accessories. Cute look, dependable precision.',
	 '/uploads/CompaEke/CompaEke7.jpg',
	 'MTEN (Point 10)','MTEN (Point 10)', TRUE, 'LSP19', NULL),
	
	('SP1908','Thien Long Y C-020 compass set',
	 'Versatile compass set with extra lead and classroom-ready case. Built for smooth adjustments and accurate drafting.',
	 '/uploads/CompaEke/CompaEke8.jpg',
	 'Thien Long','Thien Long', TRUE, 'LSP19', NULL),
	
	('SP1909','Compa MTEN Point 10 TP-C012 TSTT4 Elsa',
	 'Kid-friendly themed compass with protected needle, smooth hinge, and snug caseâ€”fun design that still draws precise circles.',
	 '/uploads/CompaEke/CompaEke9.jpg',
	 'MTEN (Point 10)','MTEN (Point 10)', TRUE, 'LSP19', NULL),
	
	('SP1910','Compa MTEN Point 10 TP-C012',
	 'Everyday student compass with reliable locking, steady lead pressure, and compact storage case for school use.',
	 '/uploads/CompaEke/CompaEke10.jpg',
	 'MTEN (Point 10)','MTEN (Point 10)', TRUE, 'LSP19', NULL),

-- LSP20 PencilEraser
	('SP2001','Clear Rotation PLUS Pokemon - Dimensions 66 x 21 x 13 mm',
	 'Compact PLUS PokÃ©mon-themed eraser in a clear rotating sleeve. Clean erasing with low residue; pocket-friendly size 66Ã—21Ã—13 mm.',
	 '/uploads/PencilEraser/PencilEraser1.jpg',
	 'PLUS','PLUS', TRUE, 'LSP20', NULL),
	
	('SP2002','Staedtler 52635N lead eraser',
	 'STAEDTLER vinyl eraser for graphite pencils. Smooth, smudge-free erasing with minimal crumbs on most paper types.',
	 '/uploads/PencilEraser/PencilEraser2.jpg',
	 'STAEDTLER','STAEDTLER', TRUE, 'LSP20', NULL),
	
	('SP2003','Thien Long car lead eraser TP-E020/CA',
	 'Novelty car-shaped eraser by Thien Long. Soft formula erases cleanlyâ€”fun and safe for school use.',
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
	 'Official PLUS small black PokÃ©mon/Pikachu eraser. Neat erasing in a compact, collectible block.',
	 '/uploads/PencilEraser/PencilEraser8.jpg',
	 'PLUS','PLUS', TRUE, 'LSP20', NULL),
	
	('SP2009','Staedtler 526 35B black pencil eraser',
	 'STAEDTLER 526 35B black eraserâ€”dust-reduced, smear-resistant, and suitable for everyday writing or sketching.',
	 '/uploads/PencilEraser/PencilEraser9.jpg',
	 'STAEDTLER','STAEDTLER', TRUE, 'LSP20', NULL),
	
	('SP2010','Genuine Plus pencil eraser, Pokemon and Pikachu yellow tablets',
	 'Official PLUS small yellow PokÃ©mon/Pikachu eraser block. Soft, clean erase with a playful theme.',
	 '/uploads/PencilEraser/PencilEraser10.jpg',
	 'PLUS','PLUS', TRUE, 'LSP20', NULL);


-- =========================
-- INITIAL IMPORT INVOICE
-- =========================
INSERT INTO import_invoice (import_invoice_code, discount, total_amount, description, employee_code, supplier_code, status)
VALUES ('PN01', 0, 2500000, 'Phiếu nhập lần đầu tiên', 'NV_KETOAN', 'NCC01', 'APPROVED');


-- =========================
-- IMPORT INVOICE DETAILS (ALL PRODUCTS)
-- ========================
INSERT INTO import_invoice_detail (import_invoice_detail_code, quantity_imported, import_price, total_amount, product_code, import_invoice_code) VALUES
    -- LSP01 Romance
    ('CTPN001', 1000, 50000, 500000, 'SP0101','PN01'),
    ('CTPN002', 1000, 50000, 500000, 'SP0102','PN01'),
    ('CTPN003', 1000, 50000, 500000, 'SP0103','PN01'),
    ('CTPN004', 1000, 50000, 500000, 'SP0104','PN01'),
    ('CTPN005', 1000, 50000, 500000, 'SP0105','PN01'),
    ('CTPN006', 1000, 50000, 500000, 'SP0106','PN01'),
    ('CTPN007', 1000, 50000, 500000, 'SP0107','PN01'),
    ('CTPN008', 1000, 50000, 500000, 'SP0108','PN01'),
    ('CTPN009', 1000, 50000, 500000, 'SP0109','PN01'),
    ('CTPN010', 1000, 50000, 500000, 'SP0110','PN01'),
    
    -- LSP02 Horror
    ('CTPN011', 1000, 50000, 500000, 'SP0201','PN01'),
    ('CTPN012', 1000, 50000, 500000, 'SP0202','PN01'),
    ('CTPN013', 1000, 50000, 500000, 'SP0203','PN01'),
    ('CTPN014', 1000, 50000, 500000, 'SP0204','PN01'),
    ('CTPN015', 1000, 50000, 500000, 'SP0205','PN01'),
    ('CTPN016', 1000, 50000, 500000, 'SP0206','PN01'),
    ('CTPN017', 1000, 50000, 500000, 'SP0207','PN01'),
    ('CTPN018', 1000, 50000, 500000, 'SP0208','PN01'),
    ('CTPN019', 1000, 50000, 500000, 'SP0209','PN01'),
    ('CTPN020', 1000, 50000, 500000, 'SP0210','PN01'),
    ('CTPN021', 1000, 50000, 500000, 'SP0211','PN01'),
    ('CTPN022', 1000, 50000, 500000, 'SP0212','PN01'),
    ('CTPN023', 1000, 50000, 500000, 'SP0213','PN01'),
    ('CTPN024', 1000, 50000, 500000, 'SP0214','PN01'),
    ('CTPN025', 1000, 50000, 500000, 'SP0215','PN01'),
    ('CTPN026', 1000, 50000, 500000, 'SP0216','PN01'),
    ('CTPN027', 1000, 50000, 500000, 'SP0217','PN01'),
    ('CTPN028', 1000, 50000, 500000, 'SP0218','PN01'),
    ('CTPN029', 1000, 50000, 500000, 'SP0219','PN01'),
    ('CTPN030', 1000, 50000, 500000, 'SP0220','PN01'),
    ('CTPN031', 1000, 50000, 500000, 'SP0221','PN01'),
    ('CTPN032', 1000, 50000, 500000, 'SP0222','PN01'),
    ('CTPN033', 1000, 50000, 500000, 'SP0223','PN01'),
    ('CTPN034', 1000, 50000, 500000, 'SP0224','PN01'),
    ('CTPN035', 1000, 50000, 500000, 'SP0225','PN01'),
    ('CTPN036', 1000, 50000, 500000, 'SP0226','PN01'),
    ('CTPN037', 1000, 50000, 500000, 'SP0227','PN01'),
    ('CTPN038', 1000, 50000, 500000, 'SP0228','PN01'),
    ('CTPN039', 1000, 50000, 500000, 'SP0229','PN01'),
    ('CTPN040', 1000, 50000, 500000, 'SP0230','PN01'),
    ('CTPN041', 1000, 50000, 500000, 'SP0231','PN01'),
    ('CTPN042', 1000, 50000, 500000, 'SP0232','PN01'),
    ('CTPN043', 1000, 50000, 500000, 'SP0233','PN01'),
    ('CTPN044', 1000, 50000, 500000, 'SP0234','PN01'),
    ('CTPN045', 1000, 50000, 500000, 'SP0235','PN01'),
    ('CTPN046', 1000, 50000, 500000, 'SP0236','PN01'),
    ('CTPN047', 1000, 50000, 500000, 'SP0237','PN01'),
    ('CTPN048', 1000, 50000, 500000, 'SP0238','PN01'),
    ('CTPN049', 1000, 50000, 500000, 'SP0239','PN01'),
    ('CTPN050', 1000, 50000, 500000, 'SP0240','PN01'),
    
    -- LSP03 Fantasy
    ('CTPN051', 1000, 50000, 500000, 'SP0301','PN01'),
    ('CTPN052', 1000, 50000, 500000, 'SP0302','PN01'),
    ('CTPN053', 1000, 50000, 500000, 'SP0303','PN01'),
    ('CTPN054', 1000, 50000, 500000, 'SP0304','PN01'),
    ('CTPN055', 1000, 50000, 500000, 'SP0305','PN01'),
    ('CTPN056', 1000, 50000, 500000, 'SP0306','PN01'),
    ('CTPN057', 1000, 50000, 500000, 'SP0307','PN01'),
    ('CTPN058', 1000, 50000, 500000, 'SP0308','PN01'),
    ('CTPN059', 1000, 50000, 500000, 'SP0309','PN01'),
    ('CTPN060', 1000, 50000, 500000, 'SP0310','PN01'),
    ('CTPN061', 1000, 50000, 500000, 'SP0311','PN01'),
    ('CTPN062', 1000, 50000, 500000, 'SP0312','PN01'),
    ('CTPN063', 1000, 50000, 500000, 'SP0313','PN01'),
    ('CTPN064', 1000, 50000, 500000, 'SP0314','PN01'),
    ('CTPN065', 1000, 50000, 500000, 'SP0315','PN01'),
    ('CTPN066', 1000, 50000, 500000, 'SP0316','PN01'),
    ('CTPN067', 1000, 50000, 500000, 'SP0317','PN01'),
    ('CTPN068', 1000, 50000, 500000, 'SP0318','PN01'),
    ('CTPN069', 1000, 50000, 500000, 'SP0319','PN01'),
    ('CTPN070', 1000, 50000, 500000, 'SP0320','PN01'),
    
    -- LSP04 Business
    ('CTPN071', 1000, 50000, 500000, 'SP0401','PN01'),
    ('CTPN072', 1000, 50000, 500000, 'SP0402','PN01'),
    ('CTPN073', 1000, 50000, 500000, 'SP0403','PN01'),
    ('CTPN074', 1000, 50000, 500000, 'SP0404','PN01'),
    ('CTPN075', 1000, 50000, 500000, 'SP0405','PN01'),
    ('CTPN076', 1000, 50000, 500000, 'SP0406','PN01'),
    ('CTPN077', 1000, 50000, 500000, 'SP0407','PN01'),
    ('CTPN078', 1000, 50000, 500000, 'SP0408','PN01'),
    ('CTPN079', 1000, 50000, 500000, 'SP0409','PN01'),
    ('CTPN080', 1000, 50000, 500000, 'SP0410','PN01'),
    ('CTPN081', 1000, 50000, 500000, 'SP0411','PN01'),
    ('CTPN082', 1000, 50000, 500000, 'SP0412','PN01'),
    ('CTPN083', 1000, 50000, 500000, 'SP0413','PN01'),
    ('CTPN084', 1000, 50000, 500000, 'SP0414','PN01'),
    ('CTPN085', 1000, 50000, 500000, 'SP0415','PN01'),
    ('CTPN086', 1000, 50000, 500000, 'SP0416','PN01'),
    ('CTPN087', 1000, 50000, 500000, 'SP0417','PN01'),
    ('CTPN088', 1000, 50000, 500000, 'SP0418','PN01'),
    ('CTPN089', 1000, 50000, 500000, 'SP0419','PN01'),
    ('CTPN090', 1000, 50000, 500000, 'SP0420','PN01'),
    
    -- LSP05 Drama
    ('CTPN091', 1000, 50000, 500000, 'SP0501','PN01'),
    ('CTPN092', 1000, 50000, 500000, 'SP0502','PN01'),
    ('CTPN093', 1000, 50000, 500000, 'SP0503','PN01'),
    ('CTPN094', 1000, 50000, 500000, 'SP0504','PN01'),
    ('CTPN095', 1000, 50000, 500000, 'SP0505','PN01'),
    ('CTPN096', 1000, 50000, 500000, 'SP0506','PN01'),
    ('CTPN097', 1000, 50000, 500000, 'SP0507','PN01'),
    ('CTPN098', 1000, 50000, 500000, 'SP0508','PN01'),
    ('CTPN099', 1000, 50000, 500000, 'SP0509','PN01'),
    ('CTPN100', 1000, 50000, 500000, 'SP0510','PN01'),
    ('CTPN101', 1000, 50000, 500000, 'SP0511','PN01'),
    ('CTPN102', 1000, 50000, 500000, 'SP0512','PN01'),
    ('CTPN103', 1000, 50000, 500000, 'SP0513','PN01'),
    ('CTPN104', 1000, 50000, 500000, 'SP0514','PN01'),
    ('CTPN105', 1000, 50000, 500000, 'SP0515','PN01'),
    ('CTPN106', 1000, 50000, 500000, 'SP0516','PN01'),
    ('CTPN107', 1000, 50000, 500000, 'SP0517','PN01'),
    ('CTPN108', 1000, 50000, 500000, 'SP0518','PN01'),
    ('CTPN109', 1000, 50000, 500000, 'SP0519','PN01'),
    ('CTPN110', 1000, 50000, 500000, 'SP0520','PN01'),
    
    -- LSP06 Biography
    ('CTPN111', 1000, 50000, 500000, 'SP0601','PN01'),
    ('CTPN112', 1000, 50000, 500000, 'SP0602','PN01'),
    ('CTPN113', 1000, 50000, 500000, 'SP0603','PN01'),
    ('CTPN114', 1000, 50000, 500000, 'SP0604','PN01'),
    ('CTPN115', 1000, 50000, 500000, 'SP0605','PN01'),
    ('CTPN116', 1000, 50000, 500000, 'SP0606','PN01'),
    ('CTPN117', 1000, 50000, 500000, 'SP0607','PN01'),
    ('CTPN118', 1000, 50000, 500000, 'SP0608','PN01'),
    ('CTPN119', 1000, 50000, 500000, 'SP0609','PN01'),
    ('CTPN120', 1000, 50000, 500000, 'SP0610','PN01'),
    ('CTPN121', 1000, 50000, 500000, 'SP0611','PN01'),
    ('CTPN122', 1000, 50000, 500000, 'SP0612','PN01'),
    ('CTPN123', 1000, 50000, 500000, 'SP0613','PN01'),
    ('CTPN124', 1000, 50000, 500000, 'SP0614','PN01'),
    ('CTPN125', 1000, 50000, 500000, 'SP0615','PN01'),
    ('CTPN126', 1000, 50000, 500000, 'SP0616','PN01'),
    ('CTPN127', 1000, 50000, 500000, 'SP0617','PN01'),
    ('CTPN128', 1000, 50000, 500000, 'SP0618','PN01'),
    ('CTPN129', 1000, 50000, 500000, 'SP0619','PN01'),
    ('CTPN130', 1000, 50000, 500000, 'SP0620','PN01'),
    
    -- LSP07 Cook
    ('CTPN131', 1000, 50000, 500000, 'SP0701','PN01'),
    ('CTPN132', 1000, 50000, 500000, 'SP0702','PN01'),
    ('CTPN133', 1000, 50000, 500000, 'SP0703','PN01'),
    ('CTPN134', 1000, 50000, 500000, 'SP0704','PN01'),
    ('CTPN135', 1000, 50000, 500000, 'SP0705','PN01'),
    ('CTPN136', 1000, 50000, 500000, 'SP0706','PN01'),
    ('CTPN137', 1000, 50000, 500000, 'SP0707','PN01'),
    ('CTPN138', 1000, 50000, 500000, 'SP0708','PN01'),
    ('CTPN139', 1000, 50000, 500000, 'SP0709','PN01'),
    ('CTPN140', 1000, 50000, 500000, 'SP0710','PN01'),
    ('CTPN141', 1000, 50000, 500000, 'SP0711','PN01'),
    ('CTPN142', 1000, 50000, 500000, 'SP0712','PN01'),
    ('CTPN143', 1000, 50000, 500000, 'SP0713','PN01'),
    ('CTPN144', 1000, 50000, 500000, 'SP0714','PN01'),
    ('CTPN145', 1000, 50000, 500000, 'SP0715','PN01'),
    ('CTPN146', 1000, 50000, 500000, 'SP0716','PN01'),
    ('CTPN147', 1000, 50000, 500000, 'SP0717','PN01'),
    ('CTPN148', 1000, 50000, 500000, 'SP0718','PN01'),
    ('CTPN149', 1000, 50000, 500000, 'SP0719','PN01'),
    ('CTPN150', 1000, 50000, 500000, 'SP0720','PN01'),
    
    -- LSP08 Poetry  
    ('CTPN151', 1000, 50000, 500000, 'SP0801','PN01'),
    ('CTPN152', 1000, 50000, 500000, 'SP0802','PN01'),
    ('CTPN153', 1000, 50000, 500000, 'SP0803','PN01'),
    ('CTPN154', 1000, 50000, 500000, 'SP0804','PN01'),
    ('CTPN155', 1000, 50000, 500000, 'SP0805','PN01'),
    ('CTPN156', 1000, 50000, 500000, 'SP0806','PN01'),
    ('CTPN157', 1000, 50000, 500000, 'SP0807','PN01'),
    ('CTPN158', 1000, 50000, 500000, 'SP0808','PN01'),
    ('CTPN159', 1000, 50000, 500000, 'SP0809','PN01'),
    ('CTPN160', 1000, 50000, 500000, 'SP0810','PN01'),
    ('CTPN161', 1000, 50000, 500000, 'SP0811','PN01'),
    ('CTPN162', 1000, 50000, 500000, 'SP0812','PN01'),
    ('CTPN163', 1000, 50000, 500000, 'SP0813','PN01'),
    ('CTPN164', 1000, 50000, 500000, 'SP0814','PN01'),
    ('CTPN165', 1000, 50000, 500000, 'SP0815','PN01'),
    ('CTPN166', 1000, 50000, 500000, 'SP0816','PN01'),
    ('CTPN167', 1000, 50000, 500000, 'SP0817','PN01'),
    ('CTPN168', 1000, 50000, 500000, 'SP0818','PN01'),
    ('CTPN169', 1000, 50000, 500000, 'SP0819','PN01'),
    ('CTPN170', 1000, 50000, 500000, 'SP0820','PN01'),
    
    -- LSP09 Art
    ('CTPN171', 1000, 50000, 500000, 'SP0901','PN01'),
    ('CTPN172', 1000, 50000, 500000, 'SP0902','PN01'),
    ('CTPN173', 1000, 50000, 500000, 'SP0903','PN01'),
    ('CTPN174', 1000, 50000, 500000, 'SP0904','PN01'),
    ('CTPN175', 1000, 50000, 500000, 'SP0905','PN01'),
    ('CTPN176', 1000, 50000, 500000, 'SP0906','PN01'),
    ('CTPN177', 1000, 50000, 500000, 'SP0907','PN01'),
    ('CTPN178', 1000, 50000, 500000, 'SP0908','PN01'),
    ('CTPN179', 1000, 50000, 500000, 'SP0909','PN01'),
    ('CTPN180', 1000, 50000, 500000, 'SP0910','PN01'),
    ('CTPN181', 1000, 50000, 500000, 'SP0911','PN01'),
    ('CTPN182', 1000, 50000, 500000, 'SP0912','PN01'),
    ('CTPN183', 1000, 50000, 500000, 'SP0913','PN01'),
    ('CTPN184', 1000, 50000, 500000, 'SP0914','PN01'),
    ('CTPN185', 1000, 50000, 500000, 'SP0915','PN01'),
    ('CTPN186', 1000, 50000, 500000, 'SP0916','PN01'),
    ('CTPN187', 1000, 50000, 500000, 'SP0917','PN01'),
    
    -- LSP10 Architecture
    ('CTPN188', 1000, 50000, 500000, 'SP1001','PN01'),
    ('CTPN189', 1000, 50000, 500000, 'SP1002','PN01'),
    ('CTPN190', 1000, 50000, 500000, 'SP1003','PN01'),
    ('CTPN191', 1000, 50000, 500000, 'SP1004','PN01'),
    ('CTPN192', 1000, 50000, 500000, 'SP1005','PN01'),
    ('CTPN193', 1000, 50000, 500000, 'SP1006','PN01'),
    ('CTPN194', 1000, 50000, 500000, 'SP1007','PN01'),
    ('CTPN195', 1000, 50000, 500000, 'SP1008','PN01'),
    ('CTPN196', 1000, 50000, 500000, 'SP1009','PN01'),
    ('CTPN197', 1000, 50000, 500000, 'SP1010','PN01'),
    ('CTPN198', 1000, 50000, 500000, 'SP1011','PN01'),
    ('CTPN199', 1000, 50000, 500000, 'SP1012','PN01'),
    ('CTPN200', 1000, 50000, 500000, 'SP1013','PN01'),
    ('CTPN201', 1000, 50000, 500000, 'SP1014','PN01'),
    ('CTPN202', 1000, 50000, 500000, 'SP1015','PN01'),
    ('CTPN203', 1000, 50000, 500000, 'SP1016','PN01'),
    ('CTPN204', 1000, 50000, 500000, 'SP1017','PN01'),
    ('CTPN205', 1000, 50000, 500000, 'SP1018','PN01'),
    ('CTPN206', 1000, 50000, 500000, 'SP1019','PN01'),
    ('CTPN207', 1000, 50000, 500000, 'SP1020','PN01'),
    
    -- LSP11 Modelkit
    ('CTPN208', 1000, 80000, 800000, 'SP1101','PN01'),
    ('CTPN209', 1000, 80000, 800000, 'SP1102','PN01'),
    ('CTPN210', 1000, 80000, 800000, 'SP1103','PN01'),
    ('CTPN211', 1000, 80000, 800000, 'SP1104','PN01'),
    ('CTPN212', 1000, 80000, 800000, 'SP1105','PN01'),
    ('CTPN213', 1000, 80000, 800000, 'SP1106','PN01'),
    ('CTPN214', 1000, 80000, 800000, 'SP1107','PN01'),
    ('CTPN215', 1000, 80000, 800000, 'SP1108','PN01'),
    
    -- LSP12 Figure
    ('CTPN216', 1000, 120000, 1200000, 'SP1201','PN01'),
    ('CTPN217', 1000, 120000, 1200000, 'SP1202','PN01'),
    ('CTPN218', 1000, 120000, 1200000, 'SP1203','PN01'),
    ('CTPN219', 1000, 120000, 1200000, 'SP1204','PN01'),
    ('CTPN220', 1000, 120000, 1200000, 'SP1205','PN01'),
    ('CTPN221', 1000, 120000, 1200000, 'SP1206','PN01'),
    ('CTPN222', 1000, 120000, 1200000, 'SP1207','PN01'),
    ('CTPN223', 1000, 120000, 1200000, 'SP1208','PN01'),
    ('CTPN224', 1000, 120000, 1200000, 'SP1209','PN01'),
    ('CTPN225', 1000, 120000, 1200000, 'SP1210','PN01'),
    ('CTPN226', 1000, 120000, 1200000, 'SP1211','PN01'),
    
    -- LSP13 Calculator
    ('CTPN227', 1000, 200000, 2000000, 'SP1301','PN01'),
    ('CTPN228', 1000, 200000, 2000000, 'SP1302','PN01'),
    ('CTPN229', 1000, 200000, 2000000, 'SP1303','PN01'),
    ('CTPN230', 1000, 200000, 2000000, 'SP1304','PN01'),
    ('CTPN231', 1000, 200000, 2000000, 'SP1305','PN01'),
    
    -- LSP14 Note
    ('CTPN232', 1000, 25000, 250000, 'SP1401','PN01'),
    ('CTPN233', 1000, 25000, 250000, 'SP1402','PN01'),
    ('CTPN234', 1000, 25000, 250000, 'SP1403','PN01'),
    ('CTPN235', 1000, 25000, 250000, 'SP1404','PN01'),
    ('CTPN236', 1000, 25000, 250000, 'SP1405','PN01'),
    ('CTPN237', 1000, 25000, 250000, 'SP1406','PN01'),
    ('CTPN238', 1000, 25000, 250000, 'SP1407','PN01'),
    ('CTPN239', 1000, 25000, 250000, 'SP1408','PN01'),
    ('CTPN240', 1000, 25000, 250000, 'SP1409','PN01'),
    ('CTPN241', 1000, 25000, 250000, 'SP1410','PN01'),
    
    -- LSP15 Watch
    ('CTPN242', 1000, 300000, 3000000, 'SP1501','PN01'),
    ('CTPN243', 1000, 300000, 3000000, 'SP1502','PN01'),
    ('CTPN244', 1000, 300000, 3000000, 'SP1503','PN01'),
    ('CTPN245', 1000, 300000, 3000000, 'SP1504','PN01'),
    ('CTPN246', 1000, 300000, 3000000, 'SP1505','PN01'),
    ('CTPN247', 1000, 300000, 3000000, 'SP1506','PN01'),
    ('CTPN248', 1000, 300000, 3000000, 'SP1507','PN01'),
    ('CTPN249', 1000, 300000, 3000000, 'SP1508','PN01'),
    ('CTPN250', 1000, 300000, 3000000, 'SP1509','PN01'),
    ('CTPN251', 1000, 300000, 3000000, 'SP1510','PN01'),
    ('CTPN252', 1000, 300000, 3000000, 'SP1511','PN01'),
    
    -- LSP16 Pen
    ('CTPN253', 1000, 150000, 1500000, 'SP1601','PN01'),
    ('CTPN254', 1000, 150000, 1500000, 'SP1602','PN01'),
    ('CTPN255', 1000, 150000, 1500000, 'SP1603','PN01'),
    ('CTPN256', 1000, 150000, 1500000, 'SP1604','PN01'),
    ('CTPN257', 1000, 150000, 1500000, 'SP1605','PN01'),
    ('CTPN258', 1000, 150000, 1500000, 'SP1606','PN01'),
    ('CTPN259', 1000, 150000, 1500000, 'SP1607','PN01'),
    ('CTPN260', 1000, 150000, 1500000, 'SP1608','PN01'),
    ('CTPN261', 1000, 150000, 1500000, 'SP1609','PN01'),
    ('CTPN262', 1000, 150000, 1500000, 'SP1610','PN01'),
    ('CTPN263', 1000, 150000, 1500000, 'SP1611','PN01'),
    ('CTPN264', 1000, 150000, 1500000, 'SP1612','PN01'),
    ('CTPN265', 1000, 150000, 1500000, 'SP1613','PN01'),
    ('CTPN266', 1000, 150000, 1500000, 'SP1614','PN01'),
    ('CTPN267', 1000, 150000, 1500000, 'SP1615','PN01'),
    ('CTPN268', 1000, 150000, 1500000, 'SP1616','PN01'),
    ('CTPN269', 1000, 150000, 1500000, 'SP1617','PN01'),
    
    -- LSP17 Draw
    ('CTPN270', 1000, 35000, 350000, 'SP1701','PN01'),
    ('CTPN271', 1000, 35000, 350000, 'SP1702','PN01'),
    ('CTPN272', 1000, 35000, 350000, 'SP1703','PN01'),
    ('CTPN273', 1000, 35000, 350000, 'SP1704','PN01'),
    ('CTPN274', 1000, 35000, 350000, 'SP1705','PN01'),
    ('CTPN275', 1000, 35000, 350000, 'SP1706','PN01'),
    ('CTPN276', 1000, 35000, 350000, 'SP1707','PN01'),
    ('CTPN277', 1000, 35000, 350000, 'SP1708','PN01'),
    ('CTPN278', 1000, 35000, 350000, 'SP1709','PN01'),
    ('CTPN279', 1000, 35000, 350000, 'SP1710','PN01'),
    
    -- LSP18 Studentbook
    ('CTPN280', 1000, 30000, 300000, 'SP1801','PN01'),
    ('CTPN281', 1000, 30000, 300000, 'SP1802','PN01'),
    ('CTPN282', 1000, 30000, 300000, 'SP1803','PN01'),
    ('CTPN283', 1000, 30000, 300000, 'SP1804','PN01'),
    ('CTPN284', 1000, 30000, 300000, 'SP1805','PN01'),
    ('CTPN285', 1000, 30000, 300000, 'SP1806','PN01'),
    ('CTPN286', 1000, 30000, 300000, 'SP1807','PN01'),
    ('CTPN287', 1000, 30000, 300000, 'SP1808','PN01'),
    ('CTPN288', 1000, 30000, 300000, 'SP1809','PN01'),
    ('CTPN289', 1000, 30000, 300000, 'SP1810','PN01'),
    
    -- LSP19 CompaEke
    ('CTPN290', 1000, 20000, 200000, 'SP1901','PN01'),
    ('CTPN291', 1000, 20000, 200000, 'SP1902','PN01'),
    ('CTPN292', 1000, 20000, 200000, 'SP1903','PN01'),
    ('CTPN293', 1000, 20000, 200000, 'SP1904','PN01'),
    ('CTPN294', 1000, 20000, 200000, 'SP1905','PN01'),
    ('CTPN295', 1000, 20000, 200000, 'SP1906','PN01'),
    ('CTPN296', 1000, 20000, 200000, 'SP1907','PN01'),
    ('CTPN297', 1000, 20000, 200000, 'SP1908','PN01'),
    ('CTPN298', 1000, 20000, 200000, 'SP1909','PN01'),
    ('CTPN299', 1000, 20000, 200000, 'SP1910','PN01'),
    
    -- LSP20 PencilEraser  
    ('CTPN300', 1000, 15000, 150000, 'SP2001','PN01'),
    ('CTPN301', 1000, 15000, 150000, 'SP2002','PN01'),
    ('CTPN302', 1000, 15000, 150000, 'SP2003','PN01'),
    ('CTPN303', 1000, 15000, 150000, 'SP2004','PN01'),
    ('CTPN304', 1000, 15000, 150000, 'SP2005','PN01'),
    ('CTPN305', 1000, 15000, 150000, 'SP2006','PN01'),
    ('CTPN306', 1000, 15000, 150000, 'SP2007','PN01'),
    ('CTPN307', 1000, 15000, 150000, 'SP2008','PN01'),
    ('CTPN308', 1000, 15000, 150000, 'SP2009','PN01'),
    ('CTPN309', 1000, 15000, 150000, 'SP2010','PN01');




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
-- INVENTORY DETAIL (ALL PRODUCTS)
-- =========================

INSERT INTO inventory_detail (inventory_detail_code, inventory_code, import_invoice_code, import_invoice_detail_code, quantity) VALUES
-- LSP01 Romance
('INVDET_demo01','INV_SP0101','PN01','CTPN001',1000),
('INVDET_demo02','INV_SP0102','PN01','CTPN002',1000),
('INVDET_demo03','INV_SP0103','PN01','CTPN003',1000),
('INVDET_demo04','INV_SP0104','PN01','CTPN004',1000),
('INVDET_demo05','INV_SP0105','PN01','CTPN005',1000),
('INVDET_demo06','INV_SP0106','PN01','CTPN006',1000),
('INVDET_demo07','INV_SP0107','PN01','CTPN007',1000),
('INVDET_demo08','INV_SP0108','PN01','CTPN008',1000),
('INVDET_demo09','INV_SP0109','PN01','CTPN009',1000),
('INVDET_demo10','INV_SP0110','PN01','CTPN010',1000),

-- LSP02 Horror
('INVDET_demo11','INV_SP0201','PN01','CTPN011',1000),
('INVDET_demo12','INV_SP0202','PN01','CTPN012',1000),
('INVDET_demo13','INV_SP0203','PN01','CTPN013',1000),
('INVDET_demo14','INV_SP0204','PN01','CTPN014',1000),
('INVDET_demo15','INV_SP0205','PN01','CTPN015',1000),
('INVDET_demo16','INV_SP0206','PN01','CTPN016',1000),
('INVDET_demo17','INV_SP0207','PN01','CTPN017',1000),
('INVDET_demo18','INV_SP0208','PN01','CTPN018',1000),
('INVDET_demo19','INV_SP0209','PN01','CTPN019',1000),
('INVDET_demo20','INV_SP0210','PN01','CTPN020',1000),
('INVDET_demo21','INV_SP0211','PN01','CTPN021',1000),
('INVDET_demo22','INV_SP0212','PN01','CTPN022',1000),
('INVDET_demo23','INV_SP0213','PN01','CTPN023',1000),
('INVDET_demo24','INV_SP0214','PN01','CTPN024',1000),
('INVDET_demo25','INV_SP0215','PN01','CTPN025',1000),
('INVDET_demo26','INV_SP0216','PN01','CTPN026',1000),
('INVDET_demo27','INV_SP0217','PN01','CTPN027',1000),
('INVDET_demo28','INV_SP0218','PN01','CTPN028',1000),
('INVDET_demo29','INV_SP0219','PN01','CTPN029',1000),
('INVDET_demo30','INV_SP0220','PN01','CTPN030',1000),
('INVDET_demo31','INV_SP0221','PN01','CTPN031',1000),
('INVDET_demo32','INV_SP0222','PN01','CTPN032',1000),
('INVDET_demo33','INV_SP0223','PN01','CTPN033',1000),
('INVDET_demo34','INV_SP0224','PN01','CTPN034',1000),
('INVDET_demo35','INV_SP0225','PN01','CTPN035',1000),
('INVDET_demo36','INV_SP0226','PN01','CTPN036',1000),
('INVDET_demo37','INV_SP0227','PN01','CTPN037',1000),
('INVDET_demo38','INV_SP0228','PN01','CTPN038',1000),
('INVDET_demo39','INV_SP0229','PN01','CTPN039',1000),
('INVDET_demo40','INV_SP0230','PN01','CTPN040',1000),
('INVDET_demo41','INV_SP0231','PN01','CTPN041',1000),
('INVDET_demo42','INV_SP0232','PN01','CTPN042',1000),
('INVDET_demo43','INV_SP0233','PN01','CTPN043',1000),
('INVDET_demo44','INV_SP0234','PN01','CTPN044',1000),
('INVDET_demo45','INV_SP0235','PN01','CTPN045',1000),
('INVDET_demo46','INV_SP0236','PN01','CTPN046',1000),
('INVDET_demo47','INV_SP0237','PN01','CTPN047',1000),
('INVDET_demo48','INV_SP0238','PN01','CTPN048',1000),
('INVDET_demo49','INV_SP0239','PN01','CTPN049',1000),
('INVDET_demo50','INV_SP0240','PN01','CTPN050',1000),

-- LSP03 Fantasy
('INVDET_demo51','INV_SP0301','PN01','CTPN051',1000),
('INVDET_demo52','INV_SP0302','PN01','CTPN052',1000),
('INVDET_demo53','INV_SP0303','PN01','CTPN053',1000),
('INVDET_demo54','INV_SP0304','PN01','CTPN054',1000),
('INVDET_demo55','INV_SP0305','PN01','CTPN055',1000),
('INVDET_demo56','INV_SP0306','PN01','CTPN056',1000),
('INVDET_demo57','INV_SP0307','PN01','CTPN057',1000),
('INVDET_demo58','INV_SP0308','PN01','CTPN058',1000),
('INVDET_demo59','INV_SP0309','PN01','CTPN059',1000),
('INVDET_demo60','INV_SP0310','PN01','CTPN060',1000),
('INVDET_demo61','INV_SP0311','PN01','CTPN061',1000),
('INVDET_demo62','INV_SP0312','PN01','CTPN062',1000),
('INVDET_demo63','INV_SP0313','PN01','CTPN063',1000),
('INVDET_demo64','INV_SP0314','PN01','CTPN064',1000),
('INVDET_demo65','INV_SP0315','PN01','CTPN065',1000),
('INVDET_demo66','INV_SP0316','PN01','CTPN066',1000),
('INVDET_demo67','INV_SP0317','PN01','CTPN067',1000),
('INVDET_demo68','INV_SP0318','PN01','CTPN068',1000),
('INVDET_demo69','INV_SP0319','PN01','CTPN069',1000),
('INVDET_demo70','INV_SP0320','PN01','CTPN070',1000),

-- LSP04 Business
('INVDET_demo71','INV_SP0401','PN01','CTPN071',1000),
('INVDET_demo72','INV_SP0402','PN01','CTPN072',1000),
('INVDET_demo73','INV_SP0403','PN01','CTPN073',1000),
('INVDET_demo74','INV_SP0404','PN01','CTPN074',1000),
('INVDET_demo75','INV_SP0405','PN01','CTPN075',1000),
('INVDET_demo76','INV_SP0406','PN01','CTPN076',1000),
('INVDET_demo77','INV_SP0407','PN01','CTPN077',1000),
('INVDET_demo78','INV_SP0408','PN01','CTPN078',1000),
('INVDET_demo79','INV_SP0409','PN01','CTPN079',1000),
('INVDET_demo80','INV_SP0410','PN01','CTPN080',1000),
('INVDET_demo81','INV_SP0411','PN01','CTPN081',1000),
('INVDET_demo82','INV_SP0412','PN01','CTPN082',1000),
('INVDET_demo83','INV_SP0413','PN01','CTPN083',1000),
('INVDET_demo84','INV_SP0414','PN01','CTPN084',1000),
('INVDET_demo85','INV_SP0415','PN01','CTPN085',1000),
('INVDET_demo86','INV_SP0416','PN01','CTPN086',1000),
('INVDET_demo87','INV_SP0417','PN01','CTPN087',1000),
('INVDET_demo88','INV_SP0418','PN01','CTPN088',1000),
('INVDET_demo89','INV_SP0419','PN01','CTPN089',1000),
('INVDET_demo90','INV_SP0420','PN01','CTPN090',1000),

-- LSP05 Drama
('INVDET_demo91','INV_SP0501','PN01','CTPN091',1000),
('INVDET_demo92','INV_SP0502','PN01','CTPN092',1000),
('INVDET_demo93','INV_SP0503','PN01','CTPN093',1000),
('INVDET_demo94','INV_SP0504','PN01','CTPN094',1000),
('INVDET_demo95','INV_SP0505','PN01','CTPN095',1000),
('INVDET_demo96','INV_SP0506','PN01','CTPN096',1000),
('INVDET_demo97','INV_SP0507','PN01','CTPN097',1000),
('INVDET_demo98','INV_SP0508','PN01','CTPN098',1000),
('INVDET_demo99','INV_SP0509','PN01','CTPN099',1000),
('INVDET_demo100','INV_SP0510','PN01','CTPN100',1000),
('INVDET_demo101','INV_SP0511','PN01','CTPN101',1000),
('INVDET_demo102','INV_SP0512','PN01','CTPN102',1000),
('INVDET_demo103','INV_SP0513','PN01','CTPN103',1000),
('INVDET_demo104','INV_SP0514','PN01','CTPN104',1000),
('INVDET_demo105','INV_SP0515','PN01','CTPN105',1000),
('INVDET_demo106','INV_SP0516','PN01','CTPN106',1000),
('INVDET_demo107','INV_SP0517','PN01','CTPN107',1000),
('INVDET_demo108','INV_SP0518','PN01','CTPN108',1000),
('INVDET_demo109','INV_SP0519','PN01','CTPN109',1000),
('INVDET_demo110','INV_SP0520','PN01','CTPN110',1000),

-- LSP06 Biography
('INVDET_demo111','INV_SP0601','PN01','CTPN111',1000),
('INVDET_demo112','INV_SP0602','PN01','CTPN112',1000),
('INVDET_demo113','INV_SP0603','PN01','CTPN113',1000),
('INVDET_demo114','INV_SP0604','PN01','CTPN114',1000),
('INVDET_demo115','INV_SP0605','PN01','CTPN115',1000),
('INVDET_demo116','INV_SP0606','PN01','CTPN116',1000),
('INVDET_demo117','INV_SP0607','PN01','CTPN117',1000),
('INVDET_demo118','INV_SP0608','PN01','CTPN118',1000),
('INVDET_demo119','INV_SP0609','PN01','CTPN119',1000),
('INVDET_demo120','INV_SP0610','PN01','CTPN120',1000),
('INVDET_demo121','INV_SP0611','PN01','CTPN121',1000),
('INVDET_demo122','INV_SP0612','PN01','CTPN122',1000),
('INVDET_demo123','INV_SP0613','PN01','CTPN123',1000),
('INVDET_demo124','INV_SP0614','PN01','CTPN124',1000),
('INVDET_demo125','INV_SP0615','PN01','CTPN125',1000),
('INVDET_demo126','INV_SP0616','PN01','CTPN126',1000),
('INVDET_demo127','INV_SP0617','PN01','CTPN127',1000),
('INVDET_demo128','INV_SP0618','PN01','CTPN128',1000),
('INVDET_demo129','INV_SP0619','PN01','CTPN129',1000),
('INVDET_demo130','INV_SP0620','PN01','CTPN130',1000),

-- LSP07 Cook
('INVDET_demo131','INV_SP0701','PN01','CTPN131',1000),
('INVDET_demo132','INV_SP0702','PN01','CTPN132',1000),
('INVDET_demo133','INV_SP0703','PN01','CTPN133',1000),
('INVDET_demo134','INV_SP0704','PN01','CTPN134',1000),
('INVDET_demo135','INV_SP0705','PN01','CTPN135',1000),
('INVDET_demo136','INV_SP0706','PN01','CTPN136',1000),
('INVDET_demo137','INV_SP0707','PN01','CTPN137',1000),
('INVDET_demo138','INV_SP0708','PN01','CTPN138',1000),
('INVDET_demo139','INV_SP0709','PN01','CTPN139',1000),
('INVDET_demo140','INV_SP0710','PN01','CTPN140',1000),
('INVDET_demo141','INV_SP0711','PN01','CTPN141',1000),
('INVDET_demo142','INV_SP0712','PN01','CTPN142',1000),
('INVDET_demo143','INV_SP0713','PN01','CTPN143',1000),
('INVDET_demo144','INV_SP0714','PN01','CTPN144',1000),
('INVDET_demo145','INV_SP0715','PN01','CTPN145',1000),
('INVDET_demo146','INV_SP0716','PN01','CTPN146',1000),
('INVDET_demo147','INV_SP0717','PN01','CTPN147',1000),
('INVDET_demo148','INV_SP0718','PN01','CTPN148',1000),
('INVDET_demo149','INV_SP0719','PN01','CTPN149',1000),
('INVDET_demo150','INV_SP0720','PN01','CTPN150',1000),

-- LSP08 Poetry
('INVDET_demo151','INV_SP0801','PN01','CTPN151',1000),
('INVDET_demo152','INV_SP0802','PN01','CTPN152',1000),
('INVDET_demo153','INV_SP0803','PN01','CTPN153',1000),
('INVDET_demo154','INV_SP0804','PN01','CTPN154',1000),
('INVDET_demo155','INV_SP0805','PN01','CTPN155',1000),
('INVDET_demo156','INV_SP0806','PN01','CTPN156',1000),
('INVDET_demo157','INV_SP0807','PN01','CTPN157',1000),
('INVDET_demo158','INV_SP0808','PN01','CTPN158',1000),
('INVDET_demo159','INV_SP0809','PN01','CTPN159',1000),
('INVDET_demo160','INV_SP0810','PN01','CTPN160',1000),
('INVDET_demo161','INV_SP0811','PN01','CTPN161',1000),
('INVDET_demo162','INV_SP0812','PN01','CTPN162',1000),
('INVDET_demo163','INV_SP0813','PN01','CTPN163',1000),
('INVDET_demo164','INV_SP0814','PN01','CTPN164',1000),
('INVDET_demo165','INV_SP0815','PN01','CTPN165',1000),
('INVDET_demo166','INV_SP0816','PN01','CTPN166',1000),
('INVDET_demo167','INV_SP0817','PN01','CTPN167',1000),
('INVDET_demo168','INV_SP0818','PN01','CTPN168',1000),
('INVDET_demo169','INV_SP0819','PN01','CTPN169',1000),
('INVDET_demo170','INV_SP0820','PN01','CTPN170',1000),

-- LSP09 Art
('INVDET_demo171','INV_SP0901','PN01','CTPN171',1000),
('INVDET_demo172','INV_SP0902','PN01','CTPN172',1000),
('INVDET_demo173','INV_SP0903','PN01','CTPN173',1000),
('INVDET_demo174','INV_SP0904','PN01','CTPN174',1000),
('INVDET_demo175','INV_SP0905','PN01','CTPN175',1000),
('INVDET_demo176','INV_SP0906','PN01','CTPN176',1000),
('INVDET_demo177','INV_SP0907','PN01','CTPN177',1000),
('INVDET_demo178','INV_SP0908','PN01','CTPN178',1000),
('INVDET_demo179','INV_SP0909','PN01','CTPN179',1000),
('INVDET_demo180','INV_SP0910','PN01','CTPN180',1000),
('INVDET_demo181','INV_SP0911','PN01','CTPN181',1000),
('INVDET_demo182','INV_SP0912','PN01','CTPN182',1000),
('INVDET_demo183','INV_SP0913','PN01','CTPN183',1000),
('INVDET_demo184','INV_SP0914','PN01','CTPN184',1000),
('INVDET_demo185','INV_SP0915','PN01','CTPN185',1000),
('INVDET_demo186','INV_SP0916','PN01','CTPN186',1000),
('INVDET_demo187','INV_SP0917','PN01','CTPN187',1000),

-- LSP10 Architecture
('INVDET_demo188','INV_SP1001','PN01','CTPN188',1000),
('INVDET_demo189','INV_SP1002','PN01','CTPN189',1000),
('INVDET_demo190','INV_SP1003','PN01','CTPN190',1000),
('INVDET_demo191','INV_SP1004','PN01','CTPN191',1000),
('INVDET_demo192','INV_SP1005','PN01','CTPN192',1000),
('INVDET_demo193','INV_SP1006','PN01','CTPN193',1000),
('INVDET_demo194','INV_SP1007','PN01','CTPN194',1000),
('INVDET_demo195','INV_SP1008','PN01','CTPN195',1000),
('INVDET_demo196','INV_SP1009','PN01','CTPN196',1000),
('INVDET_demo197','INV_SP1010','PN01','CTPN197',1000),
('INVDET_demo198','INV_SP1011','PN01','CTPN198',1000),
('INVDET_demo199','INV_SP1012','PN01','CTPN199',1000),
('INVDET_demo200','INV_SP1013','PN01','CTPN200',1000),
('INVDET_demo201','INV_SP1014','PN01','CTPN201',1000),
('INVDET_demo202','INV_SP1015','PN01','CTPN202',1000),
('INVDET_demo203','INV_SP1016','PN01','CTPN203',1000),
('INVDET_demo204','INV_SP1017','PN01','CTPN204',1000),
('INVDET_demo205','INV_SP1018','PN01','CTPN205',1000),
('INVDET_demo206','INV_SP1019','PN01','CTPN206',1000),
('INVDET_demo207','INV_SP1020','PN01','CTPN207',1000),

-- LSP11 Modelkit
('INVDET_demo208','INV_SP1101','PN01','CTPN208',1000),
('INVDET_demo209','INV_SP1102','PN01','CTPN209',1000),
('INVDET_demo210','INV_SP1103','PN01','CTPN210',1000),
('INVDET_demo211','INV_SP1104','PN01','CTPN211',1000),
('INVDET_demo212','INV_SP1105','PN01','CTPN212',1000),
('INVDET_demo213','INV_SP1106','PN01','CTPN213',1000),
('INVDET_demo214','INV_SP1107','PN01','CTPN214',1000),
('INVDET_demo215','INV_SP1108','PN01','CTPN215',1000),

-- LSP12 Figure
('INVDET_demo216','INV_SP1201','PN01','CTPN216',1000),
('INVDET_demo217','INV_SP1202','PN01','CTPN217',1000),
('INVDET_demo218','INV_SP1203','PN01','CTPN218',1000),
('INVDET_demo219','INV_SP1204','PN01','CTPN219',1000),
('INVDET_demo220','INV_SP1205','PN01','CTPN220',1000),
('INVDET_demo221','INV_SP1206','PN01','CTPN221',1000),
('INVDET_demo222','INV_SP1207','PN01','CTPN222',1000),
('INVDET_demo223','INV_SP1208','PN01','CTPN223',1000),
('INVDET_demo224','INV_SP1209','PN01','CTPN224',1000),
('INVDET_demo225','INV_SP1210','PN01','CTPN225',1000),
('INVDET_demo226','INV_SP1211','PN01','CTPN226',1000),

-- LSP13 Calculator
('INVDET_demo227','INV_SP1301','PN01','CTPN227',1000),
('INVDET_demo228','INV_SP1302','PN01','CTPN228',1000),
('INVDET_demo229','INV_SP1303','PN01','CTPN229',1000),
('INVDET_demo230','INV_SP1304','PN01','CTPN230',1000),
('INVDET_demo231','INV_SP1305','PN01','CTPN231',1000),

-- LSP14 Note
('INVDET_demo232','INV_SP1401','PN01','CTPN232',1000),
('INVDET_demo233','INV_SP1402','PN01','CTPN233',1000),
('INVDET_demo234','INV_SP1403','PN01','CTPN234',1000),
('INVDET_demo235','INV_SP1404','PN01','CTPN235',1000),
('INVDET_demo236','INV_SP1405','PN01','CTPN236',1000),
('INVDET_demo237','INV_SP1406','PN01','CTPN237',1000),
('INVDET_demo238','INV_SP1407','PN01','CTPN238',1000),
('INVDET_demo239','INV_SP1408','PN01','CTPN239',1000),
('INVDET_demo240','INV_SP1409','PN01','CTPN240',1000),
('INVDET_demo241','INV_SP1410','PN01','CTPN241',1000),

-- LSP15 Watch
('INVDET_demo242','INV_SP1501','PN01','CTPN242',1000),
('INVDET_demo243','INV_SP1502','PN01','CTPN243',1000),
('INVDET_demo244','INV_SP1503','PN01','CTPN244',1000),
('INVDET_demo245','INV_SP1504','PN01','CTPN245',1000),
('INVDET_demo246','INV_SP1505','PN01','CTPN246',1000),
('INVDET_demo247','INV_SP1506','PN01','CTPN247',1000),
('INVDET_demo248','INV_SP1507','PN01','CTPN248',1000),
('INVDET_demo249','INV_SP1508','PN01','CTPN249',1000),
('INVDET_demo250','INV_SP1509','PN01','CTPN250',1000),
('INVDET_demo251','INV_SP1510','PN01','CTPN251',1000),
('INVDET_demo252','INV_SP1511','PN01','CTPN252',1000),

-- LSP16 Pen
('INVDET_demo253','INV_SP1601','PN01','CTPN253',1000),
('INVDET_demo254','INV_SP1602','PN01','CTPN254',1000),
('INVDET_demo255','INV_SP1603','PN01','CTPN255',1000),
('INVDET_demo256','INV_SP1604','PN01','CTPN256',1000),
('INVDET_demo257','INV_SP1605','PN01','CTPN257',1000),
('INVDET_demo258','INV_SP1606','PN01','CTPN258',1000),
('INVDET_demo259','INV_SP1607','PN01','CTPN259',1000),
('INVDET_demo260','INV_SP1608','PN01','CTPN260',1000),
('INVDET_demo261','INV_SP1609','PN01','CTPN261',1000),
('INVDET_demo262','INV_SP1610','PN01','CTPN262',1000),
('INVDET_demo263','INV_SP1611','PN01','CTPN263',1000),
('INVDET_demo264','INV_SP1612','PN01','CTPN264',1000),
('INVDET_demo265','INV_SP1613','PN01','CTPN265',1000),
('INVDET_demo266','INV_SP1614','PN01','CTPN266',1000),
('INVDET_demo267','INV_SP1615','PN01','CTPN267',1000),
('INVDET_demo268','INV_SP1616','PN01','CTPN268',1000),
('INVDET_demo269','INV_SP1617','PN01','CTPN269',1000),

-- LSP17 Draw
('INVDET_demo270','INV_SP1701','PN01','CTPN270',1000),
('INVDET_demo271','INV_SP1702','PN01','CTPN271',1000),
('INVDET_demo272','INV_SP1703','PN01','CTPN272',1000),
('INVDET_demo273','INV_SP1704','PN01','CTPN273',1000),
('INVDET_demo274','INV_SP1705','PN01','CTPN274',1000),
('INVDET_demo275','INV_SP1706','PN01','CTPN275',1000),
('INVDET_demo276','INV_SP1707','PN01','CTPN276',1000),
('INVDET_demo277','INV_SP1708','PN01','CTPN277',1000),
('INVDET_demo278','INV_SP1709','PN01','CTPN278',1000),
('INVDET_demo279','INV_SP1710','PN01','CTPN279',1000),

-- LSP18 Studentbook
('INVDET_demo280','INV_SP1801','PN01','CTPN280',1000),
('INVDET_demo281','INV_SP1802','PN01','CTPN281',1000),
('INVDET_demo282','INV_SP1803','PN01','CTPN282',1000),
('INVDET_demo283','INV_SP1804','PN01','CTPN283',1000),
('INVDET_demo284','INV_SP1805','PN01','CTPN284',1000),
('INVDET_demo285','INV_SP1806','PN01','CTPN285',1000),
('INVDET_demo286','INV_SP1807','PN01','CTPN286',1000),
('INVDET_demo287','INV_SP1808','PN01','CTPN287',1000),
('INVDET_demo288','INV_SP1809','PN01','CTPN288',1000),
('INVDET_demo289','INV_SP1810','PN01','CTPN289',1000),

-- LSP19 CompaEke
('INVDET_demo290','INV_SP1901','PN01','CTPN290',1000),
('INVDET_demo291','INV_SP1902','PN01','CTPN291',1000),
('INVDET_demo292','INV_SP1903','PN01','CTPN292',1000),
('INVDET_demo293','INV_SP1904','PN01','CTPN293',1000),
('INVDET_demo294','INV_SP1905','PN01','CTPN294',1000),
('INVDET_demo295','INV_SP1906','PN01','CTPN295',1000),
('INVDET_demo296','INV_SP1907','PN01','CTPN296',1000),
('INVDET_demo297','INV_SP1908','PN01','CTPN297',1000),
('INVDET_demo298','INV_SP1909','PN01','CTPN298',1000),
('INVDET_demo299','INV_SP1910','PN01','CTPN299',1000),

-- LSP20 PencilEraser
('INVDET_demo300','INV_SP2001','PN01','CTPN300',1000),
('INVDET_demo301','INV_SP2002','PN01','CTPN301',1000),
('INVDET_demo302','INV_SP2003','PN01','CTPN302',1000),
('INVDET_demo303','INV_SP2004','PN01','CTPN303',1000),
('INVDET_demo304','INV_SP2005','PN01','CTPN304',1000),
('INVDET_demo305','INV_SP2006','PN01','CTPN305',1000),
('INVDET_demo306','INV_SP2007','PN01','CTPN306',1000),
('INVDET_demo307','INV_SP2008','PN01','CTPN307',1000),
('INVDET_demo308','INV_SP2009','PN01','CTPN308',1000),
('INVDET_demo309','INV_SP2010','PN01','CTPN309',1000);





-- =========================
-- PRICE HISTORY (ALL PRODUCTS)
-- =========================

INSERT INTO price_history (price_history_code, unit_price, product_code, import_invoice_code, import_invoice_detail_code, status) VALUES
-- LSP01 Romance - Books (75k - 82k)
('LSG001A',75000,'SP0101','PN01','CTPN001',TRUE),
('LSG002A',68000,'SP0102','PN01','CTPN002',TRUE),
('LSG003A',52000,'SP0103','PN01','CTPN003',TRUE),
('LSG004A',63000,'SP0104','PN01','CTPN004',TRUE),
('LSG005A',71000,'SP0105','PN01','CTPN005',TRUE),
('LSG006A',79000,'SP0106','PN01','CTPN006',TRUE),
('LSG007A',81000,'SP0107','PN01','CTPN007',TRUE),
('LSG008A',76000,'SP0108','PN01','CTPN008',TRUE),
('LSG009A',73000,'SP0109','PN01','CTPN009',TRUE),
('LSG010A',82000,'SP0110','PN01','CTPN010',TRUE),

-- LSP02 Horror - Books (68k - 85k)
('LSG011A',68000,'SP0201','PN01','CTPN011',TRUE),
('LSG012A',72000,'SP0202','PN01','CTPN012',TRUE),
('LSG013A',69000,'SP0203','PN01','CTPN013',TRUE),
('LSG014A',77000,'SP0204','PN01','CTPN014',TRUE),
('LSG015A',74000,'SP0205','PN01','CTPN015',TRUE),
('LSG016A',71000,'SP0206','PN01','CTPN016',TRUE),
('LSG017A',85000,'SP0207','PN01','CTPN017',TRUE),
('LSG018A',78000,'SP0208','PN01','CTPN018',TRUE),
('LSG019A',76000,'SP0209','PN01','CTPN019',TRUE),
('LSG020A',83000,'SP0210','PN01','CTPN020',TRUE),
('LSG021A',69000,'SP0211','PN01','CTPN021',TRUE),
('LSG022A',72000,'SP0212','PN01','CTPN022',TRUE),
('LSG023A',75000,'SP0213','PN01','CTPN023',TRUE),
('LSG024A',70000,'SP0214','PN01','CTPN024',TRUE),
('LSG025A',73000,'SP0215','PN01','CTPN025',TRUE),
('LSG026A',77000,'SP0216','PN01','CTPN026',TRUE),
('LSG027A',80000,'SP0217','PN01','CTPN027',TRUE),
('LSG028A',74000,'SP0218','PN01','CTPN028',TRUE),
('LSG029A',78000,'SP0219','PN01','CTPN029',TRUE),
('LSG030A',81000,'SP0220','PN01','CTPN030',TRUE),
('LSG031A',76000,'SP0221','PN01','CTPN031',TRUE),
('LSG032A',79000,'SP0222','PN01','CTPN032',TRUE),
('LSG033A',82000,'SP0223','PN01','CTPN033',TRUE),
('LSG034A',75000,'SP0224','PN01','CTPN034',TRUE),
('LSG035A',73000,'SP0225','PN01','CTPN035',TRUE),
('LSG036A',77000,'SP0226','PN01','CTPN036',TRUE),
('LSG037A',84000,'SP0227','PN01','CTPN037',TRUE),
('LSG038A',78000,'SP0228','PN01','CTPN038',TRUE),
('LSG039A',80000,'SP0229','PN01','CTPN039',TRUE),
('LSG040A',85000,'SP0230','PN01','CTPN040',TRUE),
('LSG041A',71000,'SP0231','PN01','CTPN041',TRUE),
('LSG042A',74000,'SP0232','PN01','CTPN042',TRUE),
('LSG043A',76000,'SP0233','PN01','CTPN043',TRUE),
('LSG044A',79000,'SP0234','PN01','CTPN044',TRUE),
('LSG045A',82000,'SP0235','PN01','CTPN045',TRUE),
('LSG046A',75000,'SP0236','PN01','CTPN046',TRUE),
('LSG047A',78000,'SP0237','PN01','CTPN047',TRUE),
('LSG048A',81000,'SP0238','PN01','CTPN048',TRUE),
('LSG049A',83000,'SP0239','PN01','CTPN049',TRUE),
('LSG050A',77000,'SP0240','PN01','CTPN050',TRUE),

-- LSP03 Fantasy - Books (72k - 88k)
('LSG051A',72000,'SP0301','PN01','CTPN051',TRUE),
('LSG052A',85000,'SP0302','PN01','CTPN052',TRUE),
('LSG053A',78000,'SP0303','PN01','CTPN053',TRUE),
('LSG054A',82000,'SP0304','PN01','CTPN054',TRUE),
('LSG055A',79000,'SP0305','PN01','CTPN055',TRUE),
('LSG056A',84000,'SP0306','PN01','CTPN056',TRUE),
('LSG057A',81000,'SP0307','PN01','CTPN057',TRUE),
('LSG058A',76000,'SP0308','PN01','CTPN058',TRUE),
('LSG059A',88000,'SP0309','PN01','CTPN059',TRUE),
('LSG060A',83000,'SP0310','PN01','CTPN060',TRUE),
('LSG061A',75000,'SP0311','PN01','CTPN061',TRUE),
('LSG062A',78000,'SP0312','PN01','CTPN062',TRUE),
('LSG063A',80000,'SP0313','PN01','CTPN063',TRUE),
('LSG064A',77000,'SP0314','PN01','CTPN064',TRUE),
('LSG065A',82000,'SP0315','PN01','CTPN065',TRUE),
('LSG066A',85000,'SP0316','PN01','CTPN066',TRUE),
('LSG067A',79000,'SP0317','PN01','CTPN067',TRUE),
('LSG068A',74000,'SP0318','PN01','CTPN068',TRUE),
('LSG069A',81000,'SP0319','PN01','CTPN069',TRUE),
('LSG070A',86000,'SP0320','PN01','CTPN070',TRUE),

-- LSP04 Business - Books (80k - 95k)
('LSG071A',89000,'SP0401','PN01','CTPN071',TRUE),
('LSG072A',92000,'SP0402','PN01','CTPN072',TRUE),
('LSG073A',87000,'SP0403','PN01','CTPN073',TRUE),
('LSG074A',95000,'SP0404','PN01','CTPN074',TRUE),
('LSG075A',88000,'SP0405','PN01','CTPN075',TRUE),
('LSG076A',84000,'SP0406','PN01','CTPN076',TRUE),
('LSG077A',91000,'SP0407','PN01','CTPN077',TRUE),
('LSG078A',86000,'SP0408','PN01','CTPN078',TRUE),
('LSG079A',83000,'SP0409','PN01','CTPN079',TRUE),
('LSG080A',90000,'SP0410','PN01','CTPN080',TRUE),
('LSG081A',85000,'SP0411','PN01','CTPN081',TRUE),
('LSG082A',88000,'SP0412','PN01','CTPN082',TRUE),
('LSG083A',82000,'SP0413','PN01','CTPN083',TRUE),
('LSG084A',94000,'SP0414','PN01','CTPN084',TRUE),
('LSG085A',87000,'SP0415','PN01','CTPN085',TRUE),
('LSG086A',89000,'SP0416','PN01','CTPN086',TRUE),
('LSG087A',93000,'SP0417','PN01','CTPN087',TRUE),
('LSG088A',86000,'SP0418','PN01','CTPN088',TRUE),
('LSG089A',91000,'SP0419','PN01','CTPN089',TRUE),
('LSG090A',95000,'SP0420','PN01','CTPN090',TRUE),

-- LSP05 Drama - Books (70k - 85k)
('LSG091A',75000,'SP0501','PN01','CTPN091',TRUE),
('LSG092A',78000,'SP0502','PN01','CTPN092',TRUE),
('LSG093A',72000,'SP0503','PN01','CTPN093',TRUE),
('LSG094A',80000,'SP0504','PN01','CTPN094',TRUE),
('LSG095A',76000,'SP0505','PN01','CTPN095',TRUE),
('LSG096A',83000,'SP0506','PN01','CTPN096',TRUE),
('LSG097A',79000,'SP0507','PN01','CTPN097',TRUE),
('LSG098A',74000,'SP0508','PN01','CTPN098',TRUE),
('LSG099A',81000,'SP0509','PN01','CTPN099',TRUE),
('LSG100A',85000,'SP0510','PN01','CTPN100',TRUE),
('LSG101A',73000,'SP0511','PN01','CTPN101',TRUE),
('LSG102A',77000,'SP0512','PN01','CTPN102',TRUE),
('LSG103A',75000,'SP0513','PN01','CTPN103',TRUE),
('LSG104A',82000,'SP0514','PN01','CTPN104',TRUE),
('LSG105A',78000,'SP0515','PN01','CTPN105',TRUE),
('LSG106A',84000,'SP0516','PN01','CTPN106',TRUE),
('LSG107A',76000,'SP0517','PN01','CTPN107',TRUE),
('LSG108A',79000,'SP0518','PN01','CTPN108',TRUE),
('LSG109A',81000,'SP0519','PN01','CTPN109',TRUE),
('LSG110A',85000,'SP0520','PN01','CTPN110',TRUE),

-- LSP06 Biography - Books (75k - 90k)
('LSG111A',82000,'SP0601','PN01','CTPN111',TRUE),
('LSG112A',85000,'SP0602','PN01','CTPN112',TRUE),
('LSG113A',78000,'SP0603','PN01','CTPN113',TRUE),
('LSG114A',87000,'SP0604','PN01','CTPN114',TRUE),
('LSG115A',80000,'SP0605','PN01','CTPN115',TRUE),
('LSG116A',83000,'SP0606','PN01','CTPN116',TRUE),
('LSG117A',86000,'SP0607','PN01','CTPN117',TRUE),
('LSG118A',79000,'SP0608','PN01','CTPN118',TRUE),
('LSG119A',84000,'SP0609','PN01','CTPN119',TRUE),
('LSG120A',90000,'SP0610','PN01','CTPN120',TRUE),
('LSG121A',77000,'SP0611','PN01','CTPN121',TRUE),
('LSG122A',81000,'SP0612','PN01','CTPN122',TRUE),
('LSG123A',85000,'SP0613','PN01','CTPN123',TRUE),
('LSG124A',88000,'SP0614','PN01','CTPN124',TRUE),
('LSG125A',82000,'SP0615','PN01','CTPN125',TRUE),
('LSG126A',76000,'SP0616','PN01','CTPN126',TRUE),
('LSG127A',89000,'SP0617','PN01','CTPN127',TRUE),
('LSG128A',83000,'SP0618','PN01','CTPN128',TRUE),
('LSG129A',86000,'SP0619','PN01','CTPN129',TRUE),
('LSG130A',90000,'SP0620','PN01','CTPN130',TRUE),

-- LSP07 Cook - Books (65k - 80k)
('LSG131A',70000,'SP0701','PN01','CTPN131',TRUE),
('LSG132A',75000,'SP0702','PN01','CTPN132',TRUE),
('LSG133A',68000,'SP0703','PN01','CTPN133',TRUE),
('LSG134A',72000,'SP0704','PN01','CTPN134',TRUE),
('LSG135A',77000,'SP0705','PN01','CTPN135',TRUE),
('LSG136A',74000,'SP0706','PN01','CTPN136',TRUE),
('LSG137A',79000,'SP0707','PN01','CTPN137',TRUE),
('LSG138A',71000,'SP0708','PN01','CTPN138',TRUE),
('LSG139A',76000,'SP0709','PN01','CTPN139',TRUE),
('LSG140A',80000,'SP0710','PN01','CTPN140',TRUE),
('LSG141A',65000,'SP0711','PN01','CTPN141',TRUE),
('LSG142A',73000,'SP0712','PN01','CTPN142',TRUE),
('LSG143A',78000,'SP0713','PN01','CTPN143',TRUE),
('LSG144A',75000,'SP0714','PN01','CTPN144',TRUE),
('LSG145A',69000,'SP0715','PN01','CTPN145',TRUE),
('LSG146A',72000,'SP0716','PN01','CTPN146',TRUE),
('LSG147A',77000,'SP0717','PN01','CTPN147',TRUE),
('LSG148A',74000,'SP0718','PN01','CTPN148',TRUE),
('LSG149A',79000,'SP0719','PN01','CTPN149',TRUE),
('LSG150A',80000,'SP0720','PN01','CTPN150',TRUE),

-- LSP08 Poetry - Books (60k - 75k)
('LSG151A',65000,'SP0801','PN01','CTPN151',TRUE),
('LSG152A',68000,'SP0802','PN01','CTPN152',TRUE),
('LSG153A',62000,'SP0803','PN01','CTPN153',TRUE),
('LSG154A',70000,'SP0804','PN01','CTPN154',TRUE),
('LSG155A',67000,'SP0805','PN01','CTPN155',TRUE),
('LSG156A',73000,'SP0806','PN01','CTPN156',TRUE),
('LSG157A',69000,'SP0807','PN01','CTPN157',TRUE),
('LSG158A',64000,'SP0808','PN01','CTPN158',TRUE),
('LSG159A',71000,'SP0809','PN01','CTPN159',TRUE),
('LSG160A',75000,'SP0810','PN01','CTPN160',TRUE),
('LSG161A',63000,'SP0811','PN01','CTPN161',TRUE),
('LSG162A',66000,'SP0812','PN01','CTPN162',TRUE),
('LSG163A',72000,'SP0813','PN01','CTPN163',TRUE),
('LSG164A',68000,'SP0814','PN01','CTPN164',TRUE),
('LSG165A',74000,'SP0815','PN01','CTPN165',TRUE),
('LSG166A',67000,'SP0816','PN01','CTPN166',TRUE),
('LSG167A',70000,'SP0817','PN01','CTPN167',TRUE),
('LSG168A',65000,'SP0818','PN01','CTPN168',TRUE),
('LSG169A',73000,'SP0819','PN01','CTPN169',TRUE),
('LSG170A',75000,'SP0820','PN01','CTPN170',TRUE),

-- LSP09 Art - Books (85k - 120k)
('LSG171A',95000,'SP0901','PN01','CTPN171',TRUE),
('LSG172A',102000,'SP0902','PN01','CTPN172',TRUE),
('LSG173A',88000,'SP0903','PN01','CTPN173',TRUE),
('LSG174A',110000,'SP0904','PN01','CTPN174',TRUE),
('LSG175A',97000,'SP0905','PN01','CTPN175',TRUE),
('LSG176A',105000,'SP0906','PN01','CTPN176',TRUE),
('LSG177A',92000,'SP0907','PN01','CTPN177',TRUE),
('LSG178A',118000,'SP0908','PN01','CTPN178',TRUE),
('LSG179A',100000,'SP0909','PN01','CTPN179',TRUE),
('LSG180A',115000,'SP0910','PN01','CTPN180',TRUE),
('LSG181A',89000,'SP0911','PN01','CTPN181',TRUE),
('LSG182A',108000,'SP0912','PN01','CTPN182',TRUE),
('LSG183A',98000,'SP0913','PN01','CTPN183',TRUE),
('LSG184A',112000,'SP0914','PN01','CTPN184',TRUE),
('LSG185A',93000,'SP0915','PN01','CTPN185',TRUE),
('LSG186A',106000,'SP0916','PN01','CTPN186',TRUE),
('LSG187A',120000,'SP0917','PN01','CTPN187',TRUE),

-- LSP10 Architecture - Books (90k - 125k)
('LSG188A',105000,'SP1001','PN01','CTPN188',TRUE),
('LSG189A',98000,'SP1002','PN01','CTPN189',TRUE),
('LSG190A',92000,'SP1003','PN01','CTPN190',TRUE),
('LSG191A',118000,'SP1004','PN01','CTPN191',TRUE),
('LSG192A',112000,'SP1005','PN01','CTPN192',TRUE),
('LSG193A',100000,'SP1006','PN01','CTPN193',TRUE),
('LSG194A',125000,'SP1007','PN01','CTPN194',TRUE),
('LSG195A',95000,'SP1008','PN01','CTPN195',TRUE),
('LSG196A',108000,'SP1009','PN01','CTPN196',TRUE),
('LSG197A',115000,'SP1010','PN01','CTPN197',TRUE),
('LSG198A',94000,'SP1011','PN01','CTPN198',TRUE),
('LSG199A',102000,'SP1012','PN01','CTPN199',TRUE),
('LSG200A',97000,'SP1013','PN01','CTPN200',TRUE),
('LSG201A',110000,'SP1014','PN01','CTPN201',TRUE),
('LSG202A',120000,'SP1015','PN01','CTPN202',TRUE),
('LSG203A',99000,'SP1016','PN01','CTPN203',TRUE),
('LSG204A',116000,'SP1017','PN01','CTPN204',TRUE),
('LSG205A',103000,'SP1018','PN01','CTPN205',TRUE),
('LSG206A',91000,'SP1019','PN01','CTPN206',TRUE),
('LSG207A',122000,'SP1020','PN01','CTPN207',TRUE),

-- LSP11 Modelkit - Higher prices (150k - 250k)
('LSG208A',189000,'SP1101','PN01','CTPN208',TRUE),
('LSG209A',205000,'SP1102','PN01','CTPN209',TRUE),
('LSG210A',175000,'SP1103','PN01','CTPN210',TRUE),
('LSG211A',225000,'SP1104','PN01','CTPN211',TRUE),
('LSG212A',198000,'SP1105','PN01','CTPN212',TRUE),
('LSG213A',240000,'SP1106','PN01','CTPN213',TRUE),
('LSG214A',165000,'SP1107','PN01','CTPN214',TRUE),
('LSG215A',250000,'SP1108','PN01','CTPN215',TRUE),

-- LSP12 Figure - Premium prices (200k - 350k)
('LSG216A',245000,'SP1201','PN01','CTPN216',TRUE),
('LSG217A',268000,'SP1202','PN01','CTPN217',TRUE),
('LSG218A',285000,'SP1203','PN01','CTPN218',TRUE),
('LSG219A',215000,'SP1204','PN01','CTPN219',TRUE),
('LSG220A',298000,'SP1205','PN01','CTPN220',TRUE),
('LSG221A',320000,'SP1206','PN01','CTPN221',TRUE),
('LSG222A',275000,'SP1207','PN01','CTPN222',TRUE),
('LSG223A',255000,'SP1208','PN01','CTPN223',TRUE),
('LSG224A',230000,'SP1209','PN01','CTPN224',TRUE),
('LSG225A',295000,'SP1210','PN01','CTPN225',TRUE),
('LSG226A',350000,'SP1211','PN01','CTPN226',TRUE),

-- LSP13 Calculator - Tech prices (450k - 800k)
('LSG227A',589000,'SP1301','PN01','CTPN227',TRUE),
('LSG228A',612000,'SP1302','PN01','CTPN228',TRUE),
('LSG229A',595000,'SP1303','PN01','CTPN229',TRUE),
('LSG230A',608000,'SP1304','PN01','CTPN230',TRUE),
('LSG231A',785000,'SP1305','PN01','CTPN231',TRUE),

-- LSP14 Note - Low prices (35k - 55k)
('LSG232A',42000,'SP1401','PN01','CTPN232',TRUE),
('LSG233A',38000,'SP1402','PN01','CTPN233',TRUE),
('LSG234A',45000,'SP1403','PN01','CTPN234',TRUE),
('LSG235A',40000,'SP1404','PN01','CTPN235',TRUE),
('LSG236A',35000,'SP1405','PN01','CTPN236',TRUE),
('LSG237A',48000,'SP1406','PN01','CTPN237',TRUE),
('LSG238A',43000,'SP1407','PN01','CTPN238',TRUE),
('LSG239A',36000,'SP1408','PN01','CTPN239',TRUE),
('LSG240A',50000,'SP1409','PN01','CTPN240',TRUE),
('LSG241A',55000,'SP1410','PN01','CTPN241',TRUE),

-- LSP15 Watch - High tech prices (750k - 1.2M)
('LSG242A',890000,'SP1501','PN01','CTPN242',TRUE),
('LSG243A',895000,'SP1502','PN01','CTPN243',TRUE),
('LSG244A',950000,'SP1503','PN01','CTPN244',TRUE),
('LSG245A',920000,'SP1504','PN01','CTPN245',TRUE),
('LSG246A',965000,'SP1505','PN01','CTPN246',TRUE),
('LSG247A',780000,'SP1506','PN01','CTPN247',TRUE),
('LSG248A',775000,'SP1507','PN01','CTPN248',TRUE),
('LSG249A',850000,'SP1508','PN01','CTPN249',TRUE),
('LSG250A',825000,'SP1509','PN01','CTPN250',TRUE),
('LSG251A',1050000,'SP1510','PN01','CTPN251',TRUE),
('LSG252A',980000,'SP1511','PN01','CTPN252',TRUE),

-- LSP16 Pen - Premium prices (350k - 800k)
('LSG253A',485000,'SP1601','PN01','CTPN253',TRUE),
('LSG254A',750000,'SP1602','PN01','CTPN254',TRUE),
('LSG255A',520000,'SP1603','PN01','CTPN255',TRUE),
('LSG256A',580000,'SP1604','PN01','CTPN256',TRUE),
('LSG257A',465000,'SP1605','PN01','CTPN257',TRUE),
('LSG258A',380000,'SP1606','PN01','CTPN258',TRUE),
('LSG259A',420000,'SP1607','PN01','CTPN259',TRUE),
('LSG260A',395000,'SP1608','PN01','CTPN260',TRUE),
('LSG261A',800000,'SP1609','PN01','CTPN261',TRUE),
('LSG262A',450000,'SP1610','PN01','CTPN262',TRUE),
('LSG263A',510000,'SP1611','PN01','CTPN263',TRUE),
('LSG264A',375000,'SP1612','PN01','CTPN264',TRUE),
('LSG265A',440000,'SP1613','PN01','CTPN265',TRUE),
('LSG266A',365000,'SP1614','PN01','CTPN266',TRUE),
('LSG267A',490000,'SP1615','PN01','CTPN267',TRUE),
('LSG268A',385000,'SP1616','PN01','CTPN268',TRUE),
('LSG269A',395000,'SP1617','PN01','CTPN269',TRUE),

-- LSP17 Draw - Art supplies (55k - 85k)
('LSG270A',65000,'SP1701','PN01','CTPN270',TRUE),
('LSG271A',78000,'SP1702','PN01','CTPN271',TRUE),
('LSG272A',72000,'SP1703','PN01','CTPN272',TRUE),
('LSG273A',58000,'SP1704','PN01','CTPN273',TRUE),
('LSG274A',82000,'SP1705','PN01','CTPN274',TRUE),
('LSG275A',55000,'SP1706','PN01','CTPN275',TRUE),
('LSG276A',61000,'SP1707','PN01','CTPN276',TRUE),
('LSG277A',75000,'SP1708','PN01','CTPN277',TRUE),
('LSG278A',85000,'SP1709','PN01','CTPN278',TRUE),
('LSG279A',68000,'SP1710','PN01','CTPN279',TRUE),

-- LSP18 Studentbook - School supplies (45k - 65k)
('LSG280A',52000,'SP1801','PN01','CTPN280',TRUE),
('LSG281A',48000,'SP1802','PN01','CTPN281',TRUE),
('LSG282A',55000,'SP1803','PN01','CTPN282',TRUE),
('LSG283A',58000,'SP1804','PN01','CTPN283',TRUE),
('LSG284A',50000,'SP1805','PN01','CTPN284',TRUE),
('LSG285A',45000,'SP1806','PN01','CTPN285',TRUE),
('LSG286A',62000,'SP1807','PN01','CTPN286',TRUE),
('LSG287A',53000,'SP1808','PN01','CTPN287',TRUE),
('LSG288A',47000,'SP1809','PN01','CTPN288',TRUE),
('LSG289A',65000,'SP1810','PN01','CTPN289',TRUE),

-- LSP19 CompaEke - Geometry tools (35k - 60k)
('LSG290A',42000,'SP1901','PN01','CTPN290',TRUE),
('LSG291A',48000,'SP1902','PN01','CTPN291',TRUE),
('LSG292A',45000,'SP1903','PN01','CTPN292',TRUE),
('LSG293A',60000,'SP1904','PN01','CTPN293',TRUE),
('LSG294A',38000,'SP1905','PN01','CTPN294',TRUE),
('LSG295A',52000,'SP1906','PN01','CTPN295',TRUE),
('LSG296A',55000,'SP1907','PN01','CTPN296',TRUE),
('LSG297A',47000,'SP1908','PN01','CTPN297',TRUE),
('LSG298A',43000,'SP1909','PN01','CTPN298',TRUE),
('LSG299A',40000,'SP1910','PN01','CTPN299',TRUE),

-- LSP20 PencilEraser - Basic supplies (25k - 45k)
('LSG300A',35000,'SP2001','PN01','CTPN300',TRUE),
('LSG301A',28000,'SP2002','PN01','CTPN301',TRUE),
('LSG302A',32000,'SP2003','PN01','CTPN302',TRUE),
('LSG303A',30000,'SP2004','PN01','CTPN303',TRUE),
('LSG304A',38000,'SP2005','PN01','CTPN304',TRUE),
('LSG305A',33000,'SP2006','PN01','CTPN305',TRUE),
('LSG306A',36000,'SP2007','PN01','CTPN306',TRUE),
('LSG307A',42000,'SP2008','PN01','CTPN307',TRUE),
('LSG308A',29000,'SP2009','PN01','CTPN308',TRUE),
('LSG309A',45000,'SP2010','PN01','CTPN309',TRUE);

-- =========================
-- SUPPLIER PRODUCT MAPPING 
-- =========================
-- Mapping nhÃ  cung cáº¥p vá»›i sáº£n pháº©m vÃ  giÃ¡ nháº­p tÆ°Æ¡ng á»©ng

INSERT IGNORE INTO supplier_product (supplier_product_code, supplier_code, product_code, import_price, status) VALUES

-- =========================
-- NCC01 - NXB Tá»•ng Há»£p HÃ  Ná»™i: ChuyÃªn sÃ¡ch vÄƒn há»c, tiá»ƒu thuyáº¿t
-- =========================
-- LSP01 Romance (SP0101-SP0110) - GiÃ¡ nháº­p 40k-50k
('NCCSP_NCC01_SP0101','NCC01','SP0101',42000,TRUE),
('NCCSP_NCC01_SP0102','NCC01','SP0102',45000,TRUE),
('NCCSP_NCC01_SP0103','NCC01','SP0103',38000,TRUE),
('NCCSP_NCC01_SP0104','NCC01','SP0104',41000,TRUE),
('NCCSP_NCC01_SP0105','NCC01','SP0105',44000,TRUE),
('NCCSP_NCC01_SP0106','NCC01','SP0106',47000,TRUE),
('NCCSP_NCC01_SP0107','NCC01','SP0107',49000,TRUE),
('NCCSP_NCC01_SP0108','NCC01','SP0108',43000,TRUE),
('NCCSP_NCC01_SP0109','NCC01','SP0109',40000,TRUE),
('NCCSP_NCC01_SP0110','NCC01','SP0110',50000,TRUE),

-- LSP02 Horror (SP0201-SP0240) - GiÃ¡ nháº­p 45k-55k  
('NCCSP_NCC01_SP0201','NCC01','SP0201',48000,TRUE),
('NCCSP_NCC01_SP0202','NCC01','SP0202',52000,TRUE),
('NCCSP_NCC01_SP0203','NCC01','SP0203',49000,TRUE),
('NCCSP_NCC01_SP0204','NCC01','SP0204',54000,TRUE),
('NCCSP_NCC01_SP0205','NCC01','SP0205',51000,TRUE),
('NCCSP_NCC01_SP0206','NCC01','SP0206',47000,TRUE),
('NCCSP_NCC01_SP0207','NCC01','SP0207',55000,TRUE),
('NCCSP_NCC01_SP0208','NCC01','SP0208',50000,TRUE),
('NCCSP_NCC01_SP0209','NCC01','SP0209',46000,TRUE),
('NCCSP_NCC01_SP0210','NCC01','SP0210',53000,TRUE),

-- LSP06 Biography (SP0601-SP0620) - GiÃ¡ nháº­p 50k-60k
('NCCSP_NCC01_SP0601','NCC01','SP0601',55000,TRUE),
('NCCSP_NCC01_SP0602','NCC01','SP0602',58000,TRUE),
('NCCSP_NCC01_SP0603','NCC01','SP0603',52000,TRUE),
('NCCSP_NCC01_SP0604','NCC01','SP0604',59000,TRUE),
('NCCSP_NCC01_SP0605','NCC01','SP0605',54000,TRUE),
('NCCSP_NCC01_SP0606','NCC01','SP0606',56000,TRUE),
('NCCSP_NCC01_SP0607','NCC01','SP0607',60000,TRUE),
('NCCSP_NCC01_SP0608','NCC01','SP0608',53000,TRUE),
('NCCSP_NCC01_SP0609','NCC01','SP0609',57000,TRUE),
('NCCSP_NCC01_SP0610','NCC01','SP0610',61000,TRUE),

-- =========================
-- NCC02 - NXB Kim Äá»“ng: ChuyÃªn sÃ¡ch thiáº¿u nhi, giÃ¡o dá»¥c
-- =========================
-- LSP08 Poetry (SP0801-SP0820) - GiÃ¡ nháº­p 35k-45k
('NCCSP_NCC02_SP0801','NCC02','SP0801',38000,TRUE),
('NCCSP_NCC02_SP0802','NCC02','SP0802',42000,TRUE),
('NCCSP_NCC02_SP0803','NCC02','SP0803',39000,TRUE),
('NCCSP_NCC02_SP0804','NCC02','SP0804',44000,TRUE),
('NCCSP_NCC02_SP0805','NCC02','SP0805',37000,TRUE),
('NCCSP_NCC02_SP0806','NCC02','SP0806',41000,TRUE),
('NCCSP_NCC02_SP0807','NCC02','SP0807',43000,TRUE),
('NCCSP_NCC02_SP0808','NCC02','SP0808',40000,TRUE),
('NCCSP_NCC02_SP0809','NCC02','SP0809',36000,TRUE),
('NCCSP_NCC02_SP0810','NCC02','SP0810',45000,TRUE),

-- LSP07 Cook (SP0701-SP0720) - GiÃ¡ nháº­p 40k-50k
('NCCSP_NCC02_SP0701','NCC02','SP0701',43000,TRUE),
('NCCSP_NCC02_SP0702','NCC02','SP0702',47000,TRUE),
('NCCSP_NCC02_SP0703','NCC02','SP0703',41000,TRUE),
('NCCSP_NCC02_SP0704','NCC02','SP0704',45000,TRUE),
('NCCSP_NCC02_SP0705','NCC02','SP0705',49000,TRUE),
('NCCSP_NCC02_SP0706','NCC02','SP0706',44000,TRUE),
('NCCSP_NCC02_SP0707','NCC02','SP0707',50000,TRUE),
('NCCSP_NCC02_SP0708','NCC02','SP0708',42000,TRUE),
('NCCSP_NCC02_SP0709','NCC02','SP0709',46000,TRUE),
('NCCSP_NCC02_SP0710','NCC02','SP0710',48000,TRUE),

-- =========================
-- NCC03 - CÃ´ng Ty Fahasa: Äa dáº¡ng cÃ¡c loáº¡i sÃ¡ch
-- =========================
-- LSP03 Fantasy (SP0301-SP0320) - GiÃ¡ nháº­p 48k-58k
('NCCSP_NCC03_SP0301','NCC03','SP0301',51000,TRUE),
('NCCSP_NCC03_SP0302','NCC03','SP0302',56000,TRUE),
('NCCSP_NCC03_SP0303','NCC03','SP0303',52000,TRUE),
('NCCSP_NCC03_SP0304','NCC03','SP0304',55000,TRUE),
('NCCSP_NCC03_SP0305','NCC03','SP0305',53000,TRUE),
('NCCSP_NCC03_SP0306','NCC03','SP0306',57000,TRUE),
('NCCSP_NCC03_SP0307','NCC03','SP0307',54000,TRUE),
('NCCSP_NCC03_SP0308','NCC03','SP0308',50000,TRUE),
('NCCSP_NCC03_SP0309','NCC03','SP0309',58000,TRUE),
('NCCSP_NCC03_SP0310','NCC03','SP0310',56000,TRUE),

-- LSP04 Business (SP0401-SP0420) - GiÃ¡ nháº­p 55k-65k
('NCCSP_NCC03_SP0401','NCC03','SP0401',59000,TRUE),
('NCCSP_NCC03_SP0402','NCC03','SP0402',62000,TRUE),
('NCCSP_NCC03_SP0403','NCC03','SP0403',57000,TRUE),
('NCCSP_NCC03_SP0404','NCC03','SP0404',65000,TRUE),
('NCCSP_NCC03_SP0405','NCC03','SP0405',60000,TRUE),
('NCCSP_NCC03_SP0406','NCC03','SP0406',56000,TRUE),
('NCCSP_NCC03_SP0407','NCC03','SP0407',63000,TRUE),
('NCCSP_NCC03_SP0408','NCC03','SP0408',58000,TRUE),
('NCCSP_NCC03_SP0409','NCC03','SP0409',55000,TRUE),
('NCCSP_NCC03_SP0410','NCC03','SP0410',61000,TRUE),

-- LSP05 Drama (SP0501-SP0520) - GiÃ¡ nháº­p 45k-55k
('NCCSP_NCC03_SP0501','NCC03','SP0501',48000,TRUE),
('NCCSP_NCC03_SP0502','NCC03','SP0502',52000,TRUE),
('NCCSP_NCC03_SP0503','NCC03','SP0503',46000,TRUE),
('NCCSP_NCC03_SP0504','NCC03','SP0504',54000,TRUE),
('NCCSP_NCC03_SP0505','NCC03','SP0505',50000,TRUE),
('NCCSP_NCC03_SP0506','NCC03','SP0506',55000,TRUE),
('NCCSP_NCC03_SP0507','NCC03','SP0507',51000,TRUE),
('NCCSP_NCC03_SP0508','NCC03','SP0508',47000,TRUE),
('NCCSP_NCC03_SP0509','NCC03','SP0509',53000,TRUE),
('NCCSP_NCC03_SP0510','NCC03','SP0510',56000,TRUE),

-- =========================
-- NCC04 - CÃ´ng Ty PhÃ¢n Phá»‘i ThiÃªn Long: ChuyÃªn vÄƒn phÃ²ng pháº©m
-- =========================
-- LSP16 Pen (SP1601-SP1617) - GiÃ¡ nháº­p 250k-400k
('NCCSP_NCC04_SP1601','NCC04','SP1601',285000,TRUE),
('NCCSP_NCC04_SP1602','NCC04','SP1602',365000,TRUE),
('NCCSP_NCC04_SP1603','NCC04','SP1603',295000,TRUE),
('NCCSP_NCC04_SP1604','NCC04','SP1604',320000,TRUE),
('NCCSP_NCC04_SP1605','NCC04','SP1605',275000,TRUE),
('NCCSP_NCC04_SP1606','NCC04','SP1606',245000,TRUE),
('NCCSP_NCC04_SP1607','NCC04','SP1607',280000,TRUE),
('NCCSP_NCC04_SP1608','NCC04','SP1608',260000,TRUE),
('NCCSP_NCC04_SP1609','NCC04','SP1609',390000,TRUE),
('NCCSP_NCC04_SP1610','NCC04','SP1610',270000,TRUE),

-- LSP20 PencilEraser (SP2001-SP2010) - GiÃ¡ nháº­p 15k-25k
('NCCSP_NCC04_SP2001','NCC04','SP2001',18000,TRUE),
('NCCSP_NCC04_SP2002','NCC04','SP2002',15000,TRUE),
('NCCSP_NCC04_SP2003','NCC04','SP2003',17000,TRUE),
('NCCSP_NCC04_SP2004','NCC04','SP2004',16000,TRUE),
('NCCSP_NCC04_SP2005','NCC04','SP2005',20000,TRUE),
('NCCSP_NCC04_SP2006','NCC04','SP2006',17500,TRUE),
('NCCSP_NCC04_SP2007','NCC04','SP2007',19000,TRUE),
('NCCSP_NCC04_SP2008','NCC04','SP2008',22000,TRUE),
('NCCSP_NCC04_SP2009','NCC04','SP2009',15500,TRUE),
('NCCSP_NCC04_SP2010','NCC04','SP2010',24000,TRUE),

-- LSP19 CompaEke (SP1901-SP1910) - GiÃ¡ nháº­p 20k-30k
('NCCSP_NCC04_SP1901','NCC04','SP1901',23000,TRUE),
('NCCSP_NCC04_SP1902','NCC04','SP1902',26000,TRUE),
('NCCSP_NCC04_SP1903','NCC04','SP1903',24000,TRUE),
('NCCSP_NCC04_SP1904','NCC04','SP1904',30000,TRUE),
('NCCSP_NCC04_SP1905','NCC04','SP1905',21000,TRUE),
('NCCSP_NCC04_SP1906','NCC04','SP1906',28000,TRUE),
('NCCSP_NCC04_SP1907','NCC04','SP1907',29000,TRUE),
('NCCSP_NCC04_SP1908','NCC04','SP1908',25000,TRUE),
('NCCSP_NCC04_SP1909','NCC04','SP1909',23500,TRUE),
('NCCSP_NCC04_SP1910','NCC04','SP1910',22000,TRUE),

-- =========================
-- NCC05 - NhÃ  SÃ¡ch PhÆ°Æ¡ng Nam: SÃ¡ch giÃ¡o dá»¥c vÃ  há»c thuáº­t
-- =========================
-- LSP09 Art (SP0901-SP0917) - GiÃ¡ nháº­p 45k-55k
('NCCSP_NCC05_SP0901','NCC05','SP0901',48000,TRUE),
('NCCSP_NCC05_SP0902','NCC05','SP0902',52000,TRUE),
('NCCSP_NCC05_SP0903','NCC05','SP0903',46000,TRUE),
('NCCSP_NCC05_SP0904','NCC05','SP0904',54000,TRUE),
('NCCSP_NCC05_SP0905','NCC05','SP0905',50000,TRUE),
('NCCSP_NCC05_SP0906','NCC05','SP0906',49000,TRUE),
('NCCSP_NCC05_SP0907','NCC05','SP0907',53000,TRUE),
('NCCSP_NCC05_SP0908','NCC05','SP0908',47000,TRUE),
('NCCSP_NCC05_SP0909','NCC05','SP0909',51000,TRUE),
('NCCSP_NCC05_SP0910','NCC05','SP0910',55000,TRUE),

-- LSP10 Architecture (SP1001-SP1020) - GiÃ¡ nháº­p 50k-65k
('NCCSP_NCC05_SP1001','NCC05','SP1001',54000,TRUE),
('NCCSP_NCC05_SP1002','NCC05','SP1002',58000,TRUE),
('NCCSP_NCC05_SP1003','NCC05','SP1003',52000,TRUE),
('NCCSP_NCC05_SP1004','NCC05','SP1004',61000,TRUE),
('NCCSP_NCC05_SP1005','NCC05','SP1005',56000,TRUE),
('NCCSP_NCC05_SP1006','NCC05','SP1006',59000,TRUE),
('NCCSP_NCC05_SP1007','NCC05','SP1007',63000,TRUE),
('NCCSP_NCC05_SP1008','NCC05','SP1008',55000,TRUE),
('NCCSP_NCC05_SP1009','NCC05','SP1009',57000,TRUE),
('NCCSP_NCC05_SP1010','NCC05','SP1010',65000,TRUE),

-- =========================
-- NCC06 - CÃ´ng Ty VÄƒn PhÃ²ng Pháº©m Há»“ng HÃ : VÄƒn phÃ²ng pháº©m vÃ  dá»¥ng cá»¥ há»c táº­p
-- =========================
-- LSP14 Note (SP1401-SP1410) - GiÃ¡ nháº­p 20k-35k
('NCCSP_NCC06_SP1401','NCC06','SP1401',24000,TRUE),
('NCCSP_NCC06_SP1402','NCC06','SP1402',21000,TRUE),
('NCCSP_NCC06_SP1403','NCC06','SP1403',27000,TRUE),
('NCCSP_NCC06_SP1404','NCC06','SP1404',23000,TRUE),
('NCCSP_NCC06_SP1405','NCC06','SP1405',20000,TRUE),
('NCCSP_NCC06_SP1406','NCC06','SP1406',29000,TRUE),
('NCCSP_NCC06_SP1407','NCC06','SP1407',25000,TRUE),
('NCCSP_NCC06_SP1408','NCC06','SP1408',22000,TRUE),
('NCCSP_NCC06_SP1409','NCC06','SP1409',31000,TRUE),
('NCCSP_NCC06_SP1410','NCC06','SP1410',33000,TRUE),

-- LSP17 Draw (SP1701-SP1710) - GiÃ¡ nháº­p 30k-45k
('NCCSP_NCC06_SP1701','NCC06','SP1701',34000,TRUE),
('NCCSP_NCC06_SP1702','NCC06','SP1702',41000,TRUE),
('NCCSP_NCC06_SP1703','NCC06','SP1703',37000,TRUE),
('NCCSP_NCC06_SP1704','NCC06','SP1704',32000,TRUE),
('NCCSP_NCC06_SP1705','NCC06','SP1705',43000,TRUE),
('NCCSP_NCC06_SP1706','NCC06','SP1706',30000,TRUE),
('NCCSP_NCC06_SP1707','NCC06','SP1707',33000,TRUE),
('NCCSP_NCC06_SP1708','NCC06','SP1708',40000,TRUE),
('NCCSP_NCC06_SP1709','NCC06','SP1709',45000,TRUE),
('NCCSP_NCC06_SP1710','NCC06','SP1710',36000,TRUE),

-- LSP18 Studentbook (SP1801-SP1810) - GiÃ¡ nháº­p 25k-35k
('NCCSP_NCC06_SP1801','NCC06','SP1801',28000,TRUE),
('NCCSP_NCC06_SP1802','NCC06','SP1802',26000,TRUE),
('NCCSP_NCC06_SP1803','NCC06','SP1803',30000,TRUE),
('NCCSP_NCC06_SP1804','NCC06','SP1804',32000,TRUE),
('NCCSP_NCC06_SP1805','NCC06','SP1805',27000,TRUE),
('NCCSP_NCC06_SP1806','NCC06','SP1806',25000,TRUE),
('NCCSP_NCC06_SP1807','NCC06','SP1807',34000,TRUE),
('NCCSP_NCC06_SP1808','NCC06','SP1808',29000,TRUE),
('NCCSP_NCC06_SP1809','NCC06','SP1809',26500,TRUE),
('NCCSP_NCC06_SP1810','NCC06','SP1810',35000,TRUE),

-- =========================
-- NCC07 - CÃ´ng Ty ADC Book: SÃ¡ch nÆ°á»›c ngoÃ i vÃ  Ä‘áº·c biá»‡t
-- =========================
-- LSP11 Modelkit (SP1101-SP1108) - GiÃ¡ nháº­p 180k-250k
('NCCSP_NCC07_SP1101','NCC07','SP1101',220000,TRUE),
('NCCSP_NCC07_SP1102','NCC07','SP1102',195000,TRUE),
('NCCSP_NCC07_SP1103','NCC07','SP1103',235000,TRUE),
('NCCSP_NCC07_SP1104','NCC07','SP1104',210000,TRUE),
('NCCSP_NCC07_SP1105','NCC07','SP1105',245000,TRUE),
('NCCSP_NCC07_SP1106','NCC07','SP1106',225000,TRUE),
('NCCSP_NCC07_SP1107','NCC07','SP1107',185000,TRUE),
('NCCSP_NCC07_SP1108','NCC07','SP1108',250000,TRUE),

-- Má»™t sá»‘ sÃ¡ch Horror bá»• sung (SP0211-SP0220)
('NCCSP_NCC07_SP0211','NCC07','SP0211',46000,TRUE),
('NCCSP_NCC07_SP0212','NCC07','SP0212',49000,TRUE),
('NCCSP_NCC07_SP0213','NCC07','SP0213',51000,TRUE),
('NCCSP_NCC07_SP0214','NCC07','SP0214',47000,TRUE),
('NCCSP_NCC07_SP0215','NCC07','SP0215',53000,TRUE),

-- =========================
-- NCC08 - CÃ´ng Ty Äinh Tá»‹ Books: SÃ¡ch chuyÃªn ngÃ nh
-- =========================
-- LSP12 Figure (SP1201-SP1211) - GiÃ¡ nháº­p 150k-200k
('NCCSP_NCC08_SP1201','NCC08','SP1201',175000,TRUE),
('NCCSP_NCC08_SP1202','NCC08','SP1202',185000,TRUE),
('NCCSP_NCC08_SP1203','NCC08','SP1203',165000,TRUE),
('NCCSP_NCC08_SP1204','NCC08','SP1204',190000,TRUE),
('NCCSP_NCC08_SP1205','NCC08','SP1205',170000,TRUE),
('NCCSP_NCC08_SP1206','NCC08','SP1206',195000,TRUE),
('NCCSP_NCC08_SP1207','NCC08','SP1207',180000,TRUE),
('NCCSP_NCC08_SP1208','NCC08','SP1208',160000,TRUE),
('NCCSP_NCC08_SP1209','NCC08','SP1209',200000,TRUE),
('NCCSP_NCC08_SP1210','NCC08','SP1210',177000,TRUE),
('NCCSP_NCC08_SP1211','NCC08','SP1211',188000,TRUE),

-- SÃ¡ch Biography bá»• sung (SP0611-SP0620)
('NCCSP_NCC08_SP0611','NCC08','SP0611',52000,TRUE),
('NCCSP_NCC08_SP0612','NCC08','SP0612',55000,TRUE),
('NCCSP_NCC08_SP0613','NCC08','SP0613',58000,TRUE),
('NCCSP_NCC08_SP0614','NCC08','SP0614',59000,TRUE),
('NCCSP_NCC08_SP0615','NCC08','SP0615',56000,TRUE),

-- =========================
-- NCC09 - CÃ´ng Ty AZ Viá»‡t Nam: Äiá»‡n tá»­ vÃ  cÃ´ng nghá»‡
-- =========================
-- LSP13 Calculator (SP1301-SP1305) - GiÃ¡ nháº­p 380k-580k
('NCCSP_NCC09_SP1301','NCC09','SP1301',420000,TRUE),
('NCCSP_NCC09_SP1302','NCC09','SP1302',465000,TRUE),
('NCCSP_NCC09_SP1303','NCC09','SP1303',390000,TRUE),
('NCCSP_NCC09_SP1304','NCC09','SP1304',525000,TRUE),
('NCCSP_NCC09_SP1305','NCC09','SP1305',580000,TRUE),

-- LSP15 Watch (SP1501-SP1511) - GiÃ¡ nháº­p 480k-650k
('NCCSP_NCC09_SP1501','NCC09','SP1501',525000,TRUE),
('NCCSP_NCC09_SP1502','NCC09','SP1502',545000,TRUE),
('NCCSP_NCC09_SP1503','NCC09','SP1503',580000,TRUE),
('NCCSP_NCC09_SP1504','NCC09','SP1504',560000,TRUE),
('NCCSP_NCC09_SP1505','NCC09','SP1505',595000,TRUE),
('NCCSP_NCC09_SP1506','NCC09','SP1506',485000,TRUE),
('NCCSP_NCC09_SP1507','NCC09','SP1507',480000,TRUE),
('NCCSP_NCC09_SP1508','NCC09','SP1508',520000,TRUE),
('NCCSP_NCC09_SP1509','NCC09','SP1509',500000,TRUE),
('NCCSP_NCC09_SP1510','NCC09','SP1510',650000,TRUE),
('NCCSP_NCC09_SP1511','NCC09','SP1511',610000,TRUE),

-- =========================
-- NCC10 - CÃ´ng Ty SÃ¡ch Alpha Books: SÃ¡ch Ä‘a dáº¡ng
-- =========================
-- Bá»• sung cÃ¡c sáº£n pháº©m cÃ²n láº¡i Ä‘á»ƒ Ä‘áº£m báº£o Ä‘áº§y Ä‘á»§

-- LSP01 Romance cÃ²n láº¡i
-- LSP02 Horror cÃ²n láº¡i (SP0216-SP0240)
('NCCSP_NCC10_SP0216','NCC10','SP0216',48000,TRUE),
('NCCSP_NCC10_SP0217','NCC10','SP0217',52000,TRUE),
('NCCSP_NCC10_SP0218','NCC10','SP0218',49000,TRUE),
('NCCSP_NCC10_SP0219','NCC10','SP0219',54000,TRUE),
('NCCSP_NCC10_SP0220','NCC10','SP0220',51000,TRUE),
('NCCSP_NCC10_SP0221','NCC10','SP0221',47000,TRUE),
('NCCSP_NCC10_SP0222','NCC10','SP0222',50000,TRUE),
('NCCSP_NCC10_SP0223','NCC10','SP0223',53000,TRUE),
('NCCSP_NCC10_SP0224','NCC10','SP0224',48500,TRUE),
('NCCSP_NCC10_SP0225','NCC10','SP0225',51500,TRUE),

-- LSP03 Fantasy cÃ²n láº¡i (SP0311-SP0320)
('NCCSP_NCC10_SP0311','NCC10','SP0311',52000,TRUE),
('NCCSP_NCC10_SP0312','NCC10','SP0312',55000,TRUE),
('NCCSP_NCC10_SP0313','NCC10','SP0313',53000,TRUE),
('NCCSP_NCC10_SP0314','NCC10','SP0314',56000,TRUE),
('NCCSP_NCC10_SP0315','NCC10','SP0315',54000,TRUE),
('NCCSP_NCC10_SP0316','NCC10','SP0316',57000,TRUE),
('NCCSP_NCC10_SP0317','NCC10','SP0317',55500,TRUE),
('NCCSP_NCC10_SP0318','NCC10','SP0318',51500,TRUE),
('NCCSP_NCC10_SP0319','NCC10','SP0319',58000,TRUE),
('NCCSP_NCC10_SP0320','NCC10','SP0320',59000,TRUE),

-- LSP04 Business cÃ²n láº¡i (SP0411-SP0420) 
('NCCSP_NCC10_SP0411','NCC10','SP0411',57000,TRUE),
('NCCSP_NCC10_SP0412','NCC10','SP0412',60000,TRUE),
('NCCSP_NCC10_SP0413','NCC10','SP0413',56000,TRUE),
('NCCSP_NCC10_SP0414','NCC10','SP0414',63000,TRUE),
('NCCSP_NCC10_SP0415','NCC10','SP0415',59000,TRUE),
('NCCSP_NCC10_SP0416','NCC10','SP0416',61000,TRUE),
('NCCSP_NCC10_SP0417','NCC10','SP0417',64000,TRUE),
('NCCSP_NCC10_SP0418','NCC10','SP0418',58000,TRUE),
('NCCSP_NCC10_SP0419','NCC10','SP0419',62000,TRUE),
('NCCSP_NCC10_SP0420','NCC10','SP0420',65000,TRUE),

-- LSP05 Drama cÃ²n láº¡i (SP0511-SP0520)
('NCCSP_NCC10_SP0511','NCC10','SP0511',49000,TRUE),
('NCCSP_NCC10_SP0512','NCC10','SP0512',52000,TRUE),
('NCCSP_NCC10_SP0513','NCC10','SP0513',50000,TRUE),
('NCCSP_NCC10_SP0514','NCC10','SP0514',55000,TRUE),
('NCCSP_NCC10_SP0515','NCC10','SP0515',51000,TRUE),
('NCCSP_NCC10_SP0516','NCC10','SP0516',56000,TRUE),
('NCCSP_NCC10_SP0517','NCC10','SP0517',52500,TRUE),
('NCCSP_NCC10_SP0518','NCC10','SP0518',53500,TRUE),
('NCCSP_NCC10_SP0519','NCC10','SP0519',54000,TRUE),
('NCCSP_NCC10_SP0520','NCC10','SP0520',57000,TRUE),

-- LSP06 Biography cÃ²n láº¡i (SP0616-SP0620)
('NCCSP_NCC10_SP0616','NCC10','SP0616',53000,TRUE),
('NCCSP_NCC10_SP0617','NCC10','SP0617',60000,TRUE),
('NCCSP_NCC10_SP0618','NCC10','SP0618',56000,TRUE),
('NCCSP_NCC10_SP0619','NCC10','SP0619',58000,TRUE),
('NCCSP_NCC10_SP0620','NCC10','SP0620',61000,TRUE),

-- LSP07 Cook cÃ²n láº¡i (SP0711-SP0720)
('NCCSP_NCC10_SP0711','NCC10','SP0711',44000,TRUE),
('NCCSP_NCC10_SP0712','NCC10','SP0712',47000,TRUE),
('NCCSP_NCC10_SP0713','NCC10','SP0713',45000,TRUE),
('NCCSP_NCC10_SP0714','NCC10','SP0714',49000,TRUE),
('NCCSP_NCC10_SP0715','NCC10','SP0715',46000,TRUE),
('NCCSP_NCC10_SP0716','NCC10','SP0716',50000,TRUE),
('NCCSP_NCC10_SP0717','NCC10','SP0717',48000,TRUE),
('NCCSP_NCC10_SP0718','NCC10','SP0718',47500,TRUE),
('NCCSP_NCC10_SP0719','NCC10','SP0719',48500,TRUE),
('NCCSP_NCC10_SP0720','NCC10','SP0720',51000,TRUE),

-- LSP08 Poetry cÃ²n láº¡i (SP0811-SP0820)
('NCCSP_NCC10_SP0811','NCC10','SP0811',39000,TRUE),
('NCCSP_NCC10_SP0812','NCC10','SP0812',42000,TRUE),
('NCCSP_NCC10_SP0813','NCC10','SP0813',40000,TRUE),
('NCCSP_NCC10_SP0814','NCC10','SP0814',44000,TRUE),
('NCCSP_NCC10_SP0815','NCC10','SP0815',41000,TRUE),
('NCCSP_NCC10_SP0816','NCC10','SP0816',43000,TRUE),
('NCCSP_NCC10_SP0817','NCC10','SP0817',42500,TRUE),
('NCCSP_NCC10_SP0818','NCC10','SP0818',41500,TRUE),
('NCCSP_NCC10_SP0819','NCC10','SP0819',44500,TRUE),
('NCCSP_NCC10_SP0820','NCC10','SP0820',45000,TRUE),

-- LSP09 Art cÃ²n láº¡i (SP0911-SP0917)
('NCCSP_NCC10_SP0911','NCC10','SP0911',49500,TRUE),
('NCCSP_NCC10_SP0912','NCC10','SP0912',52500,TRUE),
('NCCSP_NCC10_SP0913','NCC10','SP0913',50500,TRUE),
('NCCSP_NCC10_SP0914','NCC10','SP0914',54500,TRUE),
('NCCSP_NCC10_SP0915','NCC10','SP0915',51500,TRUE),
('NCCSP_NCC10_SP0916','NCC10','SP0916',53500,TRUE),
('NCCSP_NCC10_SP0917','NCC10','SP0917',55500,TRUE),

-- LSP10 Architecture cÃ²n láº¡i (SP1011-SP1020)
('NCCSP_NCC10_SP1011','NCC10','SP1011',56500,TRUE),
('NCCSP_NCC10_SP1012','NCC10','SP1012',59500,TRUE),
('NCCSP_NCC10_SP1013','NCC10','SP1013',57500,TRUE),
('NCCSP_NCC10_SP1014','NCC10','SP1014',61500,TRUE),
('NCCSP_NCC10_SP1015','NCC10','SP1015',58500,TRUE),
('NCCSP_NCC10_SP1016','NCC10','SP1016',62500,TRUE),
('NCCSP_NCC10_SP1017','NCC10','SP1017',60500,TRUE),
('NCCSP_NCC10_SP1018','NCC10','SP1018',59000,TRUE),
('NCCSP_NCC10_SP1019','NCC10','SP1019',63000,TRUE),
('NCCSP_NCC10_SP1020','NCC10','SP1020',65500,TRUE),

-- LSP16 Pen cÃ²n láº¡i (SP1611-SP1617)
('NCCSP_NCC10_SP1611','NCC10','SP1611',290000,TRUE),
('NCCSP_NCC10_SP1612','NCC10','SP1612',255000,TRUE),
('NCCSP_NCC10_SP1613','NCC10','SP1613',305000,TRUE),
('NCCSP_NCC10_SP1614','NCC10','SP1614',250000,TRUE),
('NCCSP_NCC10_SP1615','NCC10','SP1615',330000,TRUE),
('NCCSP_NCC10_SP1616','NCC10','SP1616',265000,TRUE),
('NCCSP_NCC10_SP1617','NCC10','SP1617',275000,TRUE),

-- =========================
-- CROSS-SUPPLIER MAPPINGS (Multiple suppliers for same products)
-- =========================
-- Táº¡o sá»± cáº¡nh tranh giá»¯a cÃ¡c nhÃ  cung cáº¥p vá»›i giÃ¡ nháº­p khÃ¡c nhau cho cÃ¹ng sáº£n pháº©m

-- Romance Books - Nhiá»u NCC cung cáº¥p cÃ¹ng sáº£n pháº©m vá»›i giÃ¡ khÃ¡c nhau
('NCCSP_NCC02_SP0101','NCC02','SP0101',41500,TRUE), -- NCC02 giÃ¡ tháº¥p hÆ¡n NCC01
('NCCSP_NCC03_SP0101','NCC03','SP0101',43500,TRUE), -- NCC03 giÃ¡ cao hÆ¡n NCC01
('NCCSP_NCC10_SP0101','NCC10','SP0101',40500,TRUE), -- NCC10 giÃ¡ ráº» nháº¥t

('NCCSP_NCC02_SP0102','NCC02','SP0102',44500,TRUE),
('NCCSP_NCC05_SP0102','NCC05','SP0102',46000,TRUE),
('NCCSP_NCC10_SP0102','NCC10','SP0102',43000,TRUE),

('NCCSP_NCC01_SP0103','NCC01','SP0103',39000,TRUE), -- NCC01 cung cáº¥p thÃªm
('NCCSP_NCC03_SP0103','NCC03','SP0103',40000,TRUE),
('NCCSP_NCC06_SP0103','NCC06','SP0103',38500,TRUE), -- GiÃ¡ tá»‘t nháº¥t

-- Horror Books - Cáº¡nh tranh máº¡nh
('NCCSP_NCC02_SP0201','NCC02','SP0201',47000,TRUE), -- Tháº¥p hÆ¡n NCC01
('NCCSP_NCC03_SP0201','NCC03','SP0201',49500,TRUE),
('NCCSP_NCC05_SP0201','NCC05','SP0201',46500,TRUE), -- GiÃ¡ cáº¡nh tranh
('NCCSP_NCC08_SP0201','NCC08','SP0201',50000,TRUE),

('NCCSP_NCC05_SP0202','NCC05','SP0202',51000,TRUE),
('NCCSP_NCC07_SP0202','NCC07','SP0202',53500,TRUE),
('NCCSP_NCC10_SP0202','NCC10','SP0202',50500,TRUE),

('NCCSP_NCC02_SP0203','NCC02','SP0203',48500,TRUE),
('NCCSP_NCC04_SP0203','NCC04','SP0203',50000,TRUE),
('NCCSP_NCC09_SP0203','NCC09','SP0203',48000,TRUE), -- GiÃ¡ tá»‘t

-- Fantasy Books - Äa dáº¡ng nhÃ  cung cáº¥p
('NCCSP_NCC01_SP0301','NCC01','SP0301',50000,TRUE), -- NCC01 cáº¡nh tranh vá»›i NCC03
('NCCSP_NCC05_SP0301','NCC05','SP0301',52000,TRUE),
('NCCSP_NCC07_SP0301','NCC07','SP0301',49500,TRUE), -- GiÃ¡ tá»‘t nháº¥t

('NCCSP_NCC02_SP0302','NCC02','SP0302',55000,TRUE),
('NCCSP_NCC06_SP0302','NCC06','SP0302',57000,TRUE),
('NCCSP_NCC08_SP0302','NCC08','SP0302',54500,TRUE),

('NCCSP_NCC01_SP0303','NCC01','SP0303',51500,TRUE),
('NCCSP_NCC04_SP0303','NCC04','SP0303',53000,TRUE),
('NCCSP_NCC09_SP0303','NCC09','SP0303',50500,TRUE),

-- Business Books - Nhiá»u lá»±a chá»n
('NCCSP_NCC01_SP0401','NCC01','SP0401',58000,TRUE), -- NCC01 cáº¡nh tranh
('NCCSP_NCC02_SP0401','NCC02','SP0401',60000,TRUE),
('NCCSP_NCC05_SP0401','NCC05','SP0401',57500,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC08_SP0401','NCC08','SP0401',61000,TRUE),

('NCCSP_NCC01_SP0402','NCC01','SP0402',61000,TRUE),
('NCCSP_NCC04_SP0402','NCC04','SP0402',63000,TRUE),
('NCCSP_NCC07_SP0402','NCC07','SP0402',60500,TRUE),

-- Drama Books
('NCCSP_NCC01_SP0501','NCC01','SP0501',47000,TRUE), -- NCC01 cáº¡nh tranh vá»›i NCC03
('NCCSP_NCC02_SP0501','NCC02','SP0501',49000,TRUE),
('NCCSP_NCC06_SP0501','NCC06','SP0501',46500,TRUE), -- GiÃ¡ tá»‘t
('NCCSP_NCC08_SP0501','NCC08','SP0501',49500,TRUE),

('NCCSP_NCC01_SP0502','NCC01','SP0502',51000,TRUE),
('NCCSP_NCC05_SP0502','NCC05','SP0502',53000,TRUE),
('NCCSP_NCC09_SP0502','NCC09','SP0502',50500,TRUE),

-- Biography Books - Äa dáº¡ng nguá»“n cung
('NCCSP_NCC02_SP0601','NCC02','SP0601',54000,TRUE), -- Tháº¥p hÆ¡n NCC01
('NCCSP_NCC03_SP0601','NCC03','SP0601',56000,TRUE),
('NCCSP_NCC05_SP0601','NCC05','SP0601',53500,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC09_SP0601','NCC09','SP0601',57000,TRUE),

('NCCSP_NCC02_SP0602','NCC02','SP0602',57000,TRUE),
('NCCSP_NCC04_SP0602','NCC04','SP0602',59000,TRUE),
('NCCSP_NCC07_SP0602','NCC07','SP0602',56500,TRUE),

-- Cook Books - Cáº¡nh tranh giÃ¡
('NCCSP_NCC01_SP0701','NCC01','SP0701',42000,TRUE), -- NCC01 thÃªm vÃ o
('NCCSP_NCC03_SP0701','NCC03','SP0701',44000,TRUE),
('NCCSP_NCC06_SP0701','NCC06','SP0701',41500,TRUE), -- GiÃ¡ tá»‘t
('NCCSP_NCC08_SP0701','NCC08','SP0701',44500,TRUE),

('NCCSP_NCC01_SP0702','NCC01','SP0702',46000,TRUE),
('NCCSP_NCC05_SP0702','NCC05','SP0702',48000,TRUE),
('NCCSP_NCC09_SP0702','NCC09','SP0702',45500,TRUE),

-- Poetry Books
('NCCSP_NCC01_SP0801','NCC01','SP0801',37000,TRUE), -- NCC01 tháº¥p hÆ¡n NCC02
('NCCSP_NCC03_SP0801','NCC03','SP0801',39000,TRUE),
('NCCSP_NCC05_SP0801','NCC05','SP0801',36500,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC07_SP0801','NCC07','SP0801',39500,TRUE),

('NCCSP_NCC01_SP0802','NCC01','SP0802',41000,TRUE),
('NCCSP_NCC04_SP0802','NCC04','SP0802',43000,TRUE),
('NCCSP_NCC06_SP0802','NCC06','SP0802',40500,TRUE),

-- Art Books - Nhiá»u nhÃ  cung cáº¥p chuyÃªn nghiá»‡p
('NCCSP_NCC01_SP0901','NCC01','SP0901',47000,TRUE), -- NCC01 cáº¡nh tranh
('NCCSP_NCC03_SP0901','NCC03','SP0901',49000,TRUE),
('NCCSP_NCC06_SP0901','NCC06','SP0901',46500,TRUE), -- GiÃ¡ tá»‘t
('NCCSP_NCC07_SP0901','NCC07','SP0901',49500,TRUE),
('NCCSP_NCC08_SP0901','NCC08','SP0901',48500,TRUE),

('NCCSP_NCC02_SP0902','NCC02','SP0902',51000,TRUE),
('NCCSP_NCC04_SP0902','NCC04','SP0902',53000,TRUE),
('NCCSP_NCC09_SP0902','NCC09','SP0902',50500,TRUE),

-- Architecture Books
('NCCSP_NCC01_SP1001','NCC01','SP1001',53000,TRUE), -- NCC01 tháº¥p hÆ¡n NCC05
('NCCSP_NCC03_SP1001','NCC03','SP1001',55000,TRUE),
('NCCSP_NCC06_SP1001','NCC06','SP1001',52500,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC08_SP1001','NCC08','SP1001',56000,TRUE),

('NCCSP_NCC02_SP1002','NCC02','SP1002',57000,TRUE),
('NCCSP_NCC04_SP1002','NCC04','SP1002',59000,TRUE),
('NCCSP_NCC07_SP1002','NCC07','SP1002',56500,TRUE),

-- Modelkit - Sáº£n pháº©m cao cáº¥p nhiá»u nhÃ  cung cáº¥p
('NCCSP_NCC05_SP1101','NCC05','SP1101',215000,TRUE), -- NCC05 tháº¥p hÆ¡n NCC07
('NCCSP_NCC08_SP1101','NCC08','SP1101',225000,TRUE),
('NCCSP_NCC09_SP1101','NCC09','SP1101',210000,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC10_SP1101','NCC10','SP1101',230000,TRUE),

('NCCSP_NCC05_SP1102','NCC05','SP1102',190000,TRUE),
('NCCSP_NCC06_SP1102','NCC06','SP1102',200000,TRUE),
('NCCSP_NCC09_SP1102','NCC09','SP1102',188000,TRUE), -- GiÃ¡ tá»‘t

-- Figure - Sáº£n pháº©m premium
('NCCSP_NCC05_SP1201','NCC05','SP1201',170000,TRUE), -- NCC05 tháº¥p hÆ¡n NCC08
('NCCSP_NCC06_SP1201','NCC06','SP1201',180000,TRUE),
('NCCSP_NCC07_SP1201','NCC07','SP1201',165000,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC09_SP1201','NCC09','SP1201',185000,TRUE),

('NCCSP_NCC02_SP1202','NCC02','SP1202',180000,TRUE),
('NCCSP_NCC04_SP1202','NCC04','SP1202',190000,TRUE),
('NCCSP_NCC10_SP1202','NCC10','SP1202',178000,TRUE),

-- Calculator - Thiáº¿t bá»‹ cÃ´ng nghá»‡ nhiá»u nguá»“n
('NCCSP_NCC04_SP1301','NCC04','SP1301',415000,TRUE), -- NCC04 tháº¥p hÆ¡n NCC09
('NCCSP_NCC05_SP1301','NCC05','SP1301',425000,TRUE),
('NCCSP_NCC06_SP1301','NCC06','SP1301',410000,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC07_SP1301','NCC07','SP1301',430000,TRUE),
('NCCSP_NCC08_SP1301','NCC08','SP1301',418000,TRUE),

('NCCSP_NCC03_SP1302','NCC03','SP1302',460000,TRUE),
('NCCSP_NCC05_SP1302','NCC05','SP1302',470000,TRUE),
('NCCSP_NCC10_SP1302','NCC10','SP1302',458000,TRUE),

-- Note - VÄƒn phÃ²ng pháº©m phá»• biáº¿n
('NCCSP_NCC02_SP1401','NCC02','SP1401',23000,TRUE), -- NCC02 tháº¥p hÆ¡n NCC06
('NCCSP_NCC04_SP1401','NCC04','SP1401',25000,TRUE),
('NCCSP_NCC05_SP1401','NCC05','SP1401',22500,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC08_SP1401','NCC08','SP1401',25500,TRUE),
('NCCSP_NCC10_SP1401','NCC10','SP1401',23500,TRUE),

('NCCSP_NCC01_SP1402','NCC01','SP1402',20000,TRUE),
('NCCSP_NCC03_SP1402','NCC03','SP1402',22000,TRUE),
('NCCSP_NCC07_SP1402','NCC07','SP1402',19500,TRUE), -- GiÃ¡ tá»‘t

-- Watch - Sáº£n pháº©m cao cáº¥p
('NCCSP_NCC04_SP1501','NCC04','SP1501',520000,TRUE), -- NCC04 tháº¥p hÆ¡n NCC09
('NCCSP_NCC05_SP1501','NCC05','SP1501',530000,TRUE),
('NCCSP_NCC06_SP1501','NCC06','SP1501',515000,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC07_SP1501','NCC07','SP1501',535000,TRUE),
('NCCSP_NCC08_SP1501','NCC08','SP1501',528000,TRUE),

('NCCSP_NCC03_SP1502','NCC03','SP1502',540000,TRUE),
('NCCSP_NCC05_SP1502','NCC05','SP1502',550000,TRUE),
('NCCSP_NCC10_SP1502','NCC10','SP1502',538000,TRUE),

-- Pen - BÃºt cao cáº¥p nhiá»u nhÃ  cung cáº¥p
('NCCSP_NCC02_SP1601','NCC02','SP1601',280000,TRUE), -- NCC02 tháº¥p hÆ¡n NCC04
('NCCSP_NCC05_SP1601','NCC05','SP1601',290000,TRUE),
('NCCSP_NCC06_SP1601','NCC06','SP1601',275000,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC08_SP1601','NCC08','SP1601',295000,TRUE),
('NCCSP_NCC09_SP1601','NCC09','SP1601',288000,TRUE),

('NCCSP_NCC01_SP1602','NCC01','SP1602',360000,TRUE),
('NCCSP_NCC03_SP1602','NCC03','SP1602',370000,TRUE),
('NCCSP_NCC07_SP1602','NCC07','SP1602',358000,TRUE), -- GiÃ¡ tá»‘t

-- Draw - Dá»¥ng cá»¥ váº½ Ä‘a dáº¡ng
('NCCSP_NCC02_SP1701','NCC02','SP1701',33000,TRUE), -- NCC02 tháº¥p hÆ¡n NCC06
('NCCSP_NCC04_SP1701','NCC04','SP1701',35000,TRUE),
('NCCSP_NCC05_SP1701','NCC05','SP1701',32500,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC08_SP1701','NCC08','SP1701',36000,TRUE),
('NCCSP_NCC10_SP1701','NCC10','SP1701',33500,TRUE),

('NCCSP_NCC01_SP1702','NCC01','SP1702',40000,TRUE),
('NCCSP_NCC03_SP1702','NCC03','SP1702',42000,TRUE),
('NCCSP_NCC07_SP1702','NCC07','SP1702',39500,TRUE),

-- Studentbook - SÃ¡ch há»c sinh nhiá»u nhÃ  cung cáº¥p
('NCCSP_NCC02_SP1801','NCC02','SP1801',27000,TRUE), -- NCC02 tháº¥p hÆ¡n NCC06
('NCCSP_NCC04_SP1801','NCC04','SP1801',29000,TRUE),
('NCCSP_NCC05_SP1801','NCC05','SP1801',26500,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC08_SP1801','NCC08','SP1801',29500,TRUE),
('NCCSP_NCC09_SP1801','NCC09','SP1801',27500,TRUE),

('NCCSP_NCC01_SP1802','NCC01','SP1802',25000,TRUE),
('NCCSP_NCC03_SP1802','NCC03','SP1802',27000,TRUE),
('NCCSP_NCC07_SP1802','NCC07','SP1802',24500,TRUE), -- GiÃ¡ tá»‘t

-- CompaEke - Dá»¥ng cá»¥ hÃ¬nh há»c Ä‘a dáº¡ng
('NCCSP_NCC02_SP1901','NCC02','SP1901',22000,TRUE), -- NCC02 tháº¥p hÆ¡n NCC04
('NCCSP_NCC05_SP1901','NCC05','SP1901',24000,TRUE),
('NCCSP_NCC06_SP1901','NCC06','SP1901',21500,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC08_SP1901','NCC08','SP1901',24500,TRUE),
('NCCSP_NCC09_SP1901','NCC09','SP1901',22500,TRUE),

('NCCSP_NCC01_SP1902','NCC01','SP1902',25000,TRUE),
('NCCSP_NCC03_SP1902','NCC03','SP1902',27000,TRUE),
('NCCSP_NCC07_SP1902','NCC07','SP1902',24500,TRUE),

-- PencilEraser - Sáº£n pháº©m phá»• biáº¿n nháº¥t
('NCCSP_NCC01_SP2001','NCC01','SP2001',17000,TRUE), -- NCC01 tháº¥p hÆ¡n NCC04
('NCCSP_NCC02_SP2001','NCC02','SP2001',19000,TRUE),
('NCCSP_NCC03_SP2001','NCC03','SP2001',16500,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC05_SP2001','NCC05','SP2001',18500,TRUE),
('NCCSP_NCC06_SP2001','NCC06','SP2001',17500,TRUE),
('NCCSP_NCC07_SP2001','NCC07','SP2001',19500,TRUE),
('NCCSP_NCC08_SP2001','NCC08','SP2001',18000,TRUE),
('NCCSP_NCC09_SP2001','NCC09','SP2001',17800,TRUE),
('NCCSP_NCC10_SP2001','NCC10','SP2001',16800,TRUE), -- GiÃ¡ ráº» nháº¥t

('NCCSP_NCC01_SP2002','NCC01','SP2002',14000,TRUE),
('NCCSP_NCC03_SP2002','NCC03','SP2002',16000,TRUE),
('NCCSP_NCC05_SP2002','NCC05','SP2002',13500,TRUE), -- Tá»‘t nháº¥t
('NCCSP_NCC07_SP2002','NCC07','SP2002',16500,TRUE),
('NCCSP_NCC09_SP2002','NCC09','SP2002',14500,TRUE),

-- ThÃªm nhiá»u mapping cho cÃ¡c sáº£n pháº©m khÃ¡c Ä‘á»ƒ tÄƒng tÃ­nh Ä‘a dáº¡ng
('NCCSP_NCC01_SP0104','NCC01','SP0104',40000,TRUE),
('NCCSP_NCC02_SP0104','NCC02','SP0104',42000,TRUE),
('NCCSP_NCC08_SP0104','NCC08','SP0104',39500,TRUE), -- GiÃ¡ tá»‘t

('NCCSP_NCC03_SP0204','NCC03','SP0204',53000,TRUE),
('NCCSP_NCC06_SP0204','NCC06','SP0204',55000,TRUE),
('NCCSP_NCC09_SP0204','NCC09','SP0204',52500,TRUE), -- GiÃ¡ tá»‘t

('NCCSP_NCC02_SP0304','NCC02','SP0304',54000,TRUE),
('NCCSP_NCC04_SP0304','NCC04','SP0304',56000,TRUE),
('NCCSP_NCC07_SP0304','NCC07','SP0304',53500,TRUE), -- GiÃ¡ tá»‘t

('NCCSP_NCC04_SP0403','NCC04','SP0403',56000,TRUE),
('NCCSP_NCC06_SP0403','NCC06','SP0403',58000,TRUE),
('NCCSP_NCC09_SP0403','NCC09','SP0403',55500,TRUE), -- GiÃ¡ tá»‘t

-- ThÃªm nhiá»u sáº£n pháº©m cao cáº¥p cÃ³ nhiá»u nhÃ  cung cáº¥p
('NCCSP_NCC02_SP1103','NCC02','SP1103',230000,TRUE),
('NCCSP_NCC04_SP1103','NCC04','SP1103',240000,TRUE),
('NCCSP_NCC06_SP1103','NCC06','SP1103',225000,TRUE), -- Tá»‘t nháº¥t

('NCCSP_NCC03_SP1203','NCC03','SP1203',160000,TRUE),
('NCCSP_NCC05_SP1203','NCC05','SP1203',170000,TRUE),
('NCCSP_NCC10_SP1203','NCC10','SP1203',158000,TRUE), -- GiÃ¡ tá»‘t

('NCCSP_NCC01_SP1503','NCC01','SP1503',575000,TRUE),
('NCCSP_NCC02_SP1503','NCC02','SP1503',585000,TRUE),
('NCCSP_NCC04_SP1503','NCC04','SP1503',570000,TRUE), -- Tá»‘t nháº¥t

-- ThÃªm mapping Ä‘á»ƒ má»i sáº£n pháº©m Ä‘á»u cÃ³ Ã­t nháº¥t 2-3 nhÃ  cung cáº¥p
('NCCSP_NCC05_SP0105','NCC05','SP0105',43000,TRUE),
('NCCSP_NCC08_SP0105','NCC08','SP0105',45000,TRUE),
('NCCSP_NCC09_SP0105','NCC09','SP0105',42500,TRUE), -- GiÃ¡ tá»‘t

('NCCSP_NCC04_SP0205','NCC04','SP0205',50000,TRUE),
('NCCSP_NCC07_SP0205','NCC07','SP0205',52000,TRUE),
('NCCSP_NCC08_SP0205','NCC08','SP0205',49500,TRUE); -- GiÃ¡ tá»‘t

-- =========================
-- END CROSS-SUPPLIER MAPPINGS
-- =========================



