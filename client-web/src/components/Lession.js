import React from 'react'
import {Link} from 'react-router-dom';

const Lession = ({id, title, author}) => {
    let url = "/lession/" + id;
    return (
        <div>
            <Link to={url}>{title}</Link>
        </div>
    )
};
export default Lession;