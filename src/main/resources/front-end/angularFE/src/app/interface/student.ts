interface Student {    
    name: string,
    surname: string,
    email: string,
    password: string,
    role: string,
    token: string
}


export interface StudentRes{
    response: Student[]
}