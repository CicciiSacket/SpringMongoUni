export interface Teacher {    
    name: string,
    surname: string,
    email: string,
    password: string,
    role: string,
    token: string
}

export interface TeacherRes {    
    name: string,
    surname: string,
    email: string,
}


export interface MappedTeacher {
    [id:string]: TeacherRes,
}


