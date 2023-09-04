import fetch from 'unfetch';

const checkStatus = response => {
    if (response.ok) {
        return response;
    } else {
        let error = new Error(response.statusText);
        error.response = response;
        response.json().then(e => {
            error.error = e;
        });
        return Promise.reject(error);
    }
}

export const getAllStudents = () => fetch('/api/v1/students').then(checkStatus);

export const addNewStudent = student => 
    fetch('/students', {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(student)
    }).then(checkStatus);

export const deleteStudent = (studentId) => fetch(`/api/v1/students/${studentId}`, {
    method: 'DELETE'
}).then(checkStatus);

export const getStudentCourses = (studentId) => fetch(`/api/v1/students/${studentId}/courses`).then(checkStatus);

export const getAllCourses = () => fetch('/api/v1/courses').then(checkStatus);

export const addNewCourse = course => 
    fetch('/api/v1/courses', {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(course)
    }).then(checkStatus);

export const deleteCourse = courseId => fetch(`/api/v1/courses/${courseId}`,
    {method: 'DELETE'}).then(checkStatus);