import { User } from "../models/user";
import express, { Express,Request,Response} from "express";

const router=express.Router()

router.post('/signup', (req:Request,res:Response)=>{
    res.json(req.body)
    const newUser={
        name:req.body.name,
        email:req.body.email,
        password:req.body.password  
          }
    const user= new User(newUser)
    user.save()
    console.log(user)

})

router.post('/login',async (req:Request,res:Response)=>{
    const email=req.body.email
    const user=await User.findOne({email})

    //checking password
    const password=req.body.password
  
    
    if(user===null){
        res.status(400).send()
    }else{
        if(password===user?.password){
         res.status(200).send(user)
        }else{
            console.log(password)
            res.status(404).send()
        }
    }
})

const userRouter=router
export{userRouter}