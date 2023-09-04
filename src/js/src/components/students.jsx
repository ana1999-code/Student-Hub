import { useState, useEffect } from 'react';
import { getAllStudents, getStudentCourses, deleteStudent } from './Client.js';
import { Table, Avatar, Spin, Modal, Empty, Button } from 'antd';
import Container from './Container';
import Footer from './Footer.js';
import AddStudentForm from './forms/AddStudentForm';
import { errorNotification, successNotification } from './Notification';
import { getIndicatorIcon } from './Spin.js';


function Students() {
  const [students, setStudents] = useState([]);
  const [isFetching, setFetching] = useState(true);
  const [isAddStudentModalVisisble, setAddStudentModalVisible] = useState(false);
  const [isCoursesModalVisible, setCoursesModalVisible] = useState(false);
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    fetchAllStudents();
  }, []);

  const fetchAllStudents = () => {
    getAllStudents()
    .then(res => res.json()
    .then(students => {
      console.log(students);
      setStudents(students);
      setFetching(false);
    }))
    .catch(error => {
      console.log(error);
      errorNotification(error.error.message);
      setFetching(false);
    });
  }

  const deleteStudentById = (studentId) => {
    deleteStudent(studentId)
    .then(() => fetchAllStudents())
    .catch(error => {
      errorNotification(error.error.message);
    });
  }

  const fetchAllCoursesForStudent = (studentId) => {
      getStudentCourses(studentId)
      .then(res => res.json()
      .then(courses => {
        setCourses(courses);
        console.log(courses);
      }))
      .catch(error => {
        errorNotification(error.error.message);
      });
  }

  const openAddStudentModal = () => {
    setAddStudentModalVisible(true);
  };
  
  const closeAddStudentModal = () => {
    setAddStudentModalVisible(false);
  };

  const openCoursesModal = () => {
    setCoursesModalVisible(true);
  };
  
  const closeCoursesModal = () => {
    setCoursesModalVisible(false);
  };

  const commonElements = () => (
    <div>
      <Modal 
            title="Add New Student" 
            open={isAddStudentModalVisisble} 
            onOk={closeAddStudentModal} 
            onCancel={closeAddStudentModal}
            > 
            <AddStudentForm 
              onSuccess={()=> {
                closeAddStudentModal();
                fetchAllStudents();
                successNotification("Student added successfully!");
                }}
              onFailure={(error) => {
                errorNotification(error.error.message);
              }}/>
          </Modal>
        <Footer 
          numberOfElements={students.length} 
          handleClickEvent={openAddStudentModal}
          addElementButtonName="Add New Student"/>
    </div>
  )

  const courseColumns = [
    {
      title: 'Course',
      key: 'course.name',
      render: (course) => course.course.name
    },
    {
      title: 'Descritpion',
      key: 'course.description',
      render: (course) => course.course.description
    },
    {
      title: 'Department',
      key: 'course.department',
      render: (course) => course.course.department
    },
    {
      title: 'Teacher',
      key: 'course.teacherName',
      render: (course) => course.course.teacherName
    },
    {
      title: 'Start Date',
      key: 'startDate',
      dataIndex: 'startDate'
    },
    {
      title: 'End Date',
      key: 'endDate',
      dataIndex: 'endDate'
    },
    {
      title: 'Grade',
      key: 'grade',
      dataIndex: 'grade'
    }
  ]

  const columns = [
    {
      title: '',
      key: 'avatar',
      render: (text, student) => (
        <Avatar size = 'large'>
          {`${student.firstName.charAt(0).toUpperCase()}${student.lastName.charAt(0).toUpperCase()}`}
        </Avatar>
      )
    },
    {
      title: 'First Name',
      dataIndex: 'firstName',
      key: 'firstName'
    },
    {
      title: 'Last Name',
      dataIndex: 'lastName',
      key: 'lastName'
    },
    {
      title: 'Email',
      dataIndex: 'email',
      key: 'email'
    },
    {
      title: 'Gender',
      dataIndex: 'gender',
      key: 'gender'
    },
    {
      title: '',
      key: 'courses',
      render: (course) => (
        <div><Modal 
            title="Courses" 
            open={isCoursesModalVisible} 
            onOk={closeCoursesModal} 
            onCancel={closeCoursesModal}
            width={1000}> 
            <Table
              dataSource={courses}  
              columns={courseColumns}
              rowKey="studentId"
              pagination={courses.length > 5 ? true : false}
            />
          </Modal>
        <Button onClick={() =>{
            fetchAllCoursesForStudent(course.studentId);
            openCoursesModal();
          }}>Courses</Button>
        </div>
      )
    },
    {
      title: '',
      key: 'deleteStudent',
      render: (student) => (
        <Button type='primary' danger onClick={() => deleteStudentById(student.studentId)}>Delete</Button>
      )
    }
  ]

  if(isFetching){
    return (
      <Container>
        <Spin indicator={getIndicatorIcon()} />
      </Container>
    )
  }

  if(students && students.length){
    return (
      <Container>
        <Table 
          dataSource={students} 
          columns={columns}
          rowKey='studentId'
          pagination={{pageSize: 5}}/>
        {commonElements()}
      </Container>
    )
  }
   
  
  return (
    <Container>
      <Empty description={
        <h1>No Students Found!</h1>
      }/>
      {commonElements()}
    </Container>
  );
}

export default Students;