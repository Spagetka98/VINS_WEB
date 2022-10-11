import React, { useRef, useState } from "react";
import styled from "styled-components";
import tw from "twin.macro";
import ClockLoader from "react-spinners/ClockLoader";
import { useNavigate } from "react-router-dom";

import Grumpy from "../../assets/grumpy/grumpy.png";
import Client from "../../config/axios/client";
import { POST_CHECK_TOKEN } from "../../config/axios/endpoints";

const Landing = () => {
  const navigate = useNavigate();
  const inputRef = useRef();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const checkToken = () => {
    setError(null);
    setLoading(true);

    Client.post(POST_CHECK_TOKEN, {
      token: inputRef.current.value,
    })
      .then((response) => {
        navigate("/dashboard/" + inputRef.current.value);
      })
      .catch((error) => {
        setError("Něco není v pořádku !");
      })
      .finally(() => {
        setLoading(false);
      });
  };

  return (
    <Body>
      <Box>
        <Img src={Grumpy} alt="Logo" />
        <FormBox>
          <Title>Mávatko On-line</Title>
          <Input type="text" ref={inputRef} placeholder="Kód mávatka !" />
          {loading ? (
            <Spinner>
              <ClockLoader color="#ffb600" loading={loading} />
            </Spinner>
          ) : (
            <Button onClick={checkToken}>Vyhledat</Button>
          )}
        </FormBox>
        <ErrorMsg error={error}>{error}</ErrorMsg>
      </Box>
    </Body>
  );
};

const Body = styled.div`
  ${tw`flex h-full flex-col justify-center items-center bg-gray-50`}
`;

const Box = styled.div`
  ${tw` mx-4 my-2  flex justify-center flex-col`}
`;

const FormBox = styled.div`
  ${tw`bg-white align-self[center] w-full rounded-3xl h-52 xl:h-72 flex flex-col  border-4 border-gray-600 shadow-xl p-8 justify-around`}
`;

const Title = styled.span`
  ${tw`text-center  bg-gradient-to-r from-pink-500 to-red-300 bg-clip-text text-transparent uppercase break-words text-base sm:text-lg md:text-xl lg:text-2xl xl:text-3xl 2xl:text-4xl font-extrabold`}
`;

const Input = styled.input.attrs({
  className: "border-slate-300 focus:border-slate-500 md:text-md",
})`
  ${tw`p-1 border-b-2 focus:shadow-md outline-none font-extralight text-base lg:text-lg 2xl:text-xl transition-all`}
`;

const Img = styled.img`
  ${tw` w-[45%] h-[45%] align-self[center] object-cover`}
`;

const Button = styled.button`
  ${tw`bg-green-300 hover:bg-green-400 px-4 py-1 italic rounded-lg align-self[center] text-base lg:text-lg 2xl:text-xl font-extralight shadow-md transition-all`}
`;

const Spinner = styled.div`
  ${tw`flex justify-center`}
`;

const ErrorMsg = styled.div(({ error }) => [
  tw`opacity-0 flex justify-center text-red-500 mt-2 italic`,
  error && tw`opacity-100`,
]);
export default Landing;
