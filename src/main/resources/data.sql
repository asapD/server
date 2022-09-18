INSERT INTO store (
    store_id,
    address,
    name,
    owner,
    store_category
) VALUES (
             1,
             '경기도 가평군 가평읍 읍내리 476-4',
             '세븐일레븐 가평본점',
             1,
             'CONVENIENCE_STORE'
         );

INSERT INTO item (
    item_id,
    store_id,
    name,
    description,
    price
) VALUES (
             1,
             1,
             '한끼용 김밥',
             '한끼용 김밥입니다.',
             2000
         );
INSERT INTO item (
    item_id,
    store_id,
    name,
    description,
    price
) VALUES (
             2,
             1,
             '제로 펩시',
             '0칼로리 탄산 음료입니다.',
             2100
         );
INSERT INTO item (
    item_id,
    store_id,
    name,
    description,
    price
) VALUES (
             3,
             1,
             '하림 닭가슴살 블렉페퍼',
             '하림에서 만든 닭가슴살 블랙페퍼입니다.',
             2900
         );
INSERT INTO item (
    item_id,
    store_id,
    name,
    description,
    price
) VALUES (
             4,
             1,
             '꼬북칩 초코',
             '꼬북칩 시리즈에서 초코맛 과자입니다.',
             1500
         );
