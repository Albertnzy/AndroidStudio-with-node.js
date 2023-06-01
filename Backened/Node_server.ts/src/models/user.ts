
import mongoose from "mongoose"
type userDoc={
    name:string,
    email:string,
    password:string|number
}

const userSchema=new mongoose.Schema<userDoc>({
    name:{
        type:String,
        required:true
    },
    email:{
        type:String,
        required:true
    },
    password:{
        type:String,
        required:true,
        validate(value:string){
            if(value.toLowerCase().includes('password')){
                throw new Error('Password cannot contain "Password for safety reasons')
            }
        }
    }
})
const User=mongoose.model('User',userSchema)

export{User}

