import './App.css';
import { useState, useEffect } from 'react';
import { getAllStudents } from './Client';
import { LoadingOutlined } from '@ant-design/icons';
import { Table, Avatar, Spin } from 'antd';
import Container from './Container';

const getIndicatorIcon = () => <LoadingOutlined style={{ fontSize: 24 }} spin />;


function App() {
  const [students, setStudents] = useState([]);
  const [isFetching, setFetching] = useState(true);

  useEffect(() => {
    fetchAllStudents();
  }, []);

  const fetchAllStudents = () => {
    getAllStudents()
    .then(res => res.json()
    .then(students => {
      setStudents(students);
      setFetching(false);
    }));
  }

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
        pagination={false}/>
      </Container>
    )
  }
    
  
  return (
    <div>No Students Found!</div>
  );
}

export default App;
