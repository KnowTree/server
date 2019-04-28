import React from 'react'
import Lession from "./Lession";
import {Link} from "react-router-dom";

class LessionsView extends React.Component {
    componentWillMount() {
        this.setState({
            lessions : [
                {id :1, title : "Lession 1"},
                {id : 2 , title : "Lession 2"}
            ]
        })
    }
    render() {
        let lessions = this.state.lessions.map(lession => {
           return (
               <Lession id={lession.id} title={lession.title}/>
           )
        });
        return (
            <div>
                <Link to="/new_lession">Add lession</Link>
                {lessions}
            </div>
        )


    }
}

export default LessionsView;