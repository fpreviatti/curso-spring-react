import React, { Component } from 'react';

class FooterComponent extends Component {

    constructor(props){
        super(props)

        this.state = {

        }
    }
    render() {
        return (
            <div>
                <footer className="footer">

                <div className="text-muted">Todos os direitos reservados</div>

                </footer>
            </div>
        );
    }
}

export default FooterComponent;