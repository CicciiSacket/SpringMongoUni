export interface Student {  
    id?: string,  
    name: string,
    surname: string,
    email: string,
    password: string,
    role: string,
    token: string
}


export interface StudentRes{
    token: string,
}