import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import { useState, useEffect } from 'react';
import { getAllStudents } from './Client';
import { LoadingOutlined } from '@ant-design/icons';
import { Table, Avatar, Spin, Modal, Empty } from 'antd';
import Container from './Container';
import Footer from './Footer';
import AddStudentForm from './forms/AddStudentForm';
import { errorNotification } from './Notification';

const getIndicatorIcon = () => <LoadingOutlined style={{ fontSize: 24 }} spin />;


function App() {
  const [students, setStudents] = useState([]);
  const [isFetching, setFetching] = useState(true);
  const [isAddStudentModalVisisble, setAddStudentModalVisible] = useState(false);

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
      const message = error.error.message;
      errorNotification(message);
      setFetching(false);
    });
  }

  const openAddStudentModal = () => {
    setAddStudentModalVisible(true);
  };
  
  const closeAddStudentModal = () => {
    setAddStudentModalVisible(false);
  };

  const commonElements = () => (
    <div>
      <Modal 
            title="Add New Student" 
            open={isAddStudentModalVisisble} 
            onOk={closeAddStudentModal} 
            onCancel={closeAddStudentModal}
            style={{width: '50%'}}> 
            <AddStudentForm 
              onSuccess={()=> {
                closeAddStudentModal();
                fetchAllStudents();
                }}/>
          </Modal>
        <Footer 
          numberOfStudents={students.length} 
          handleAddStudentClickEvent={openAddStudentModal}/>
    </div>
  )

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
