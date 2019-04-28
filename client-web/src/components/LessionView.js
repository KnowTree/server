import React from 'react'
import {Link} from 'react-router-dom';


class LessionView extends React.Component {
    componentWillMount() {
        this.setState({
            knodes : [
                {
                    id : 1,
                    title : "Node 1"
                },
                {
                    id : 2,
                    title : "Node 2"
                }
            ]
        })
    }
    render() {
        let nodes = this.state.knodes.map(node => {
            return (
                <p># {node.id} {node.title}</p>
            )
        });
        return (
            <div>
            {nodes}
            </div>
        )
    }
}

export default LessionView;