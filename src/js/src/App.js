import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import { useState, useEffect } from 'react';
import { getAllStudents, getStudentCourses } from './Client';
import { LoadingOutlined } from '@ant-design/icons';
import { Table, Avatar, Spin, Modal, Empty, Button } from 'antd';
import Container from './Container';
import Footer from './Footer';
import AddStudentForm from './forms/AddStudentForm';
import { errorNotification, successNotification } from './Notification';

const getIndicatorIcon = () => <LoadingOutlined style={{ fontSize: 24 }} spin />;


function App() {
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

  const fetchAllCoursesForStudent = (studentId) => {
      getStudentCourses(studentId)
      .then(res => res.json()
      .then(courses => {
        setCourses(courses);
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
          numberOfStudents={students.length} 
          handleAddStudentClickEvent={openAddStudentModal}/>
    </div>
  )

  const courseColumns = [
    {
      title: 'Course',
      key: 'name',
      dataIndex: 'name'
    },
    {
      title: 'Descritpion',
      key: 'description',
      dataIndex: 'description'
    },
    {
      title: 'Department',
      key: 'department',
      dataIndex: 'department'
    },
    {
      title: 'Teacher',
      key: 'teacherName',
      dataIndex: 'teacherName'
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
              rowKey='courseId'
            />
          </Modal>
        <Button onClick={() =>{
            fetchAllCoursesForStudent(course.studentId);
            openCoursesModal();
          }}>Courses</Button>
        </div>
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

export default App;
