import mongoose, { ConnectOptions } from "mongoose";
mongoose.connect('mongodb://127.0.0.1:27017/userbase_api',{
    useNewUrlParser:true,
}as ConnectOptions
)

export{}