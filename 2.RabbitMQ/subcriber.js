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
        channel.assertQueue(queueName, {
            durable: false
        });
        channel.consume(queueName, (message) => {
            console.log(`Received: ${message.content}`);
            channel.ack(message);
        });
    })
})
