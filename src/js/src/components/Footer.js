import { Button, Avatar } from "antd";
import Container from "./Container";

const Footer = (props) => (
    <div className="footer">
        <Container>
            {props.numberOfElements !== undefined ? 
                <Avatar style={{backgroundColor: '#f56a00', marginRight:'5px'}} 
                size="large">
                    {props.numberOfElements}
                    </Avatar>:null}
            <Button onClick={() => props.handleClickEvent()} type="primary">{props.addElementButtonName}</Button>
        </Container>
    </div>
);

export default Footer;