import { useState, useEffect } from 'react';
import { getAllCourses } from './Client.js';
import { LoadingOutlined } from '@ant-design/icons';
import { Table, Spin, Empty } from 'antd';
import Container from './Container.js';
import Footer from './Footer.js';
import { errorNotification } from './Notification.js';

const getIndicatorIcon = () => <LoadingOutlined style={{ fontSize: 24 }} spin />;

function Courses() {
  const [courses, setCourses] = useState([]);
  const [isFetching, setFetching] = useState(true);

  useEffect(() => {
    fetchAllCourses();
  }, []);

  const fetchAllCourses = () => {
    getAllCourses()
    .then(res => res.json()
    .then(courses => {
      console.log(courses);
      setCourses(courses);
      setFetching(false);
    }))
    .catch(error => {
      console.log(error);
      errorNotification(error.error.message);
      setFetching(false);
    });
  }

  const commonElements = () => (
    <div>
        <Footer 
          numberOfElements={courses.length} 
          addElementButtonName="Add New Course" 
         />
    </div>
  )

  const columns = [
    {
      title: 'Course',
      dataIndex: 'name',
      key: 'name'
    },
    {
      title: 'Description',
      dataIndex: 'description',
      key: 'description'
    },
    {
      title: 'Department',
      dataIndex: 'department',
      key: 'department'
    },
    {
      title: 'Teacher',
      dataIndex: 'teacherName',
      key: 'teacherName'
    }
  ]

  if(isFetching){
    return (
      <Container>
        <Spin indicator={getIndicatorIcon()} />
      </Container>
    )
  }

  if(courses && courses.length){
    return (
      <Container>
        <Table 
          dataSource={courses} 
          columns={columns}
          rowKey='courseId'
          pagination={{pageSize: 5}}/>
        {commonElements()}
      </Container>
    )
  }
   
  
  return (
    <Container>
      <Empty description={
        <h1>No Courses Found!</h1>
      }/>
      {commonElements()}
    </Container>
  );
}

export default Courses;