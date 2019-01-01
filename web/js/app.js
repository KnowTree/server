'use strict';
const e = React.createElement;
const root = document.querySelector('#root');

class App extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return "React powered";
    }
}

ReactDOM.render(e(App, {}), root);