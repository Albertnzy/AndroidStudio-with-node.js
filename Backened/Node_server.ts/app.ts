import express, { Express,Request,Response } from "express";
import './src/db/mongoose'
import { userRouter } from "./src/router/user";

const app:Express=express()
app.use(express.json())
app.use(userRouter)

app.listen(3000,()=>{
   console.log('Listening on port 3000.....')
})