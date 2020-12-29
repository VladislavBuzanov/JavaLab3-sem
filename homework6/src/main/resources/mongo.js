//use database

db.users.insertMany([
        {
            name: 'Vladislav',
            years: 20
        },
        {
            name: 'Sergey',
            years: 20
        }
    ]
);

db.addresses.insertMany([
    {
        city: 'Казань',
        address: 'Улица пушкина',
        house: 'Дом колотушкина'
    },
    {
        city: 'Казань',
        address: 'Улица университетская'
    }
]);

db.products.insertMany(
    [{
        name: "product1",
        price: 2
    },
        {
            name: "product2"

        }]
)

db.orders.insertMany(
    [
        {
            user: ObjectId('5fead404b97cf77c47bc06f7'),
            address: ObjectId('5fead418b97cf77c47bc06fb')
        }]
)

db.orders.updateOne({_id: ObjectId('5fead4acb97cf77c47bc0705')},
    {
        $set: {
            status: "Confirmed"
        },
        $push: {
            product: ObjectId('5fead449b97cf77c47bc0702')
        }
    }
)