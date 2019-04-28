import React from 'react'
import {Link} from 'react-router-dom';
class SectionList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {sections : [
            {
                path : "/lessions",
                title : "Lessions"
            },
            {
                path : "/users",
                title : "Users"
            },
                {
                    path : "/settings",
                    title : "Settings"
                }
        ]};
    }

    render() {
        return (<div>
            {
                this.state.sections.map(section => {
                    let path = section.path;
                    return (
                        <Link to={path}>
                            {section.title}
                        </Link>
                    )
                })
            }
        </div>)
    }
}

export default SectionList;