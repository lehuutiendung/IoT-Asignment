const amqp = require('amqplib/callback_api');

amqp.connect(`amqp://localhost`, (err, connection) => {
    if(err){
        throw err;
    }
    connection.createChannel((err, channel) => {
        if(err){
            throw err;
        }
        let queueName = "queueMsg";
        let maxTemp = 40;
        let minTemp = 10;
        let date = new Date();
        let message = {
            id: 1,
            temperature: Math.floor(Math.random() * (maxTemp - minTemp + 1)) + minTemp,
            status: "Bình thường",
            time: date.toISOString(),
        };
        channel.assertQueue(queueName, {
            durable: false
        });
        channel.sendToQueue(queueName, Buffer.from(JSON.stringify(message)));
        setTimeout(() => {
            connection.close();
        }, 1000)
    })
})